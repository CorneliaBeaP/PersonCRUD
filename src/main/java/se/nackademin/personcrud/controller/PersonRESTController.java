package se.nackademin.personcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import se.nackademin.personcrud.domain.Person;
import se.nackademin.personcrud.domain.response.PersonResponse;
import se.nackademin.personcrud.service.PersonService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonRESTController {

    private PersonService personService;

    @Autowired
    public PersonRESTController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getAPersonByID(@PathVariable long id) {
        Person person = null;
        Optional<Person> optionalPerson = personService.findPerson(id);
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
            if (!person.hasLink("all_persons")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRESTController.class).getAllPersons()).withRel("all_persons");
                person.add(link);
            }
            if (!person.hasLink("delete_person")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRESTController.class).deletePersonByID(id)).withRel("delete_person");
                person.add(link);
            }
            if (!person.hasLink("add_person")) {
                Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(PersonRESTController.class).addPerson(person)).withRel("add_person");
                person.add(link);
            }
        }


        return person;
    }

    @GetMapping("/all")
    public List<Person> getAllPersons() {
        return personService.listAllPersons();
    }

//TODO: kolla att denna fungerar

    @PostMapping("/delete/{id}")
    public PersonResponse deletePersonByID(@PathVariable long id) {
        PersonResponse response = new PersonResponse();
        if (personService.findPerson(id).isPresent()) {
            personService.delete(id);
            if (!personService.findPerson(id).isPresent()) {
                response.setMessage("Person deleted");
            } else {
                response.setMessage("An error occured.");
            }
        } else {
            response.setMessage("Could not find a person with ID " + id + ".");
        }
        return response;

    }

    @PostMapping("/add")
    public PersonResponse addPerson(@RequestBody Person person) {
        PersonResponse response = new PersonResponse();
        if ((person.getFirstName() != null) && (person.getLastName() != null) && (person.getAddress() != null) && (person.getZipcode() != 0) && (person.getCity() != null) && (person.getEmail() != null) && (person.getGender() != null) && (person.getBornYear() != 0)) {
            personService.save(person);
            response.setMessage("Person added to database.");
        } else {
            response.setMessage("Failed to add person. Please make sure all needed information is provided.");
        }
        return response;
    }
}
