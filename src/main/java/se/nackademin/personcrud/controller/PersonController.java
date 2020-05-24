package se.nackademin.personcrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.nackademin.personcrud.domain.Person;
import se.nackademin.personcrud.service.PersonService;

import java.util.List;
import java.util.Optional;

@Controller
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String firstPageWithPersons(Model model) {
        List<Person> personList = personService.listAllPersons();
        model.addAttribute("personList", personList);
        return "index";
    }

    @GetMapping("/add")
    public String goTonewPersonPage(Model model) {
        Person person = new Person();
        model.addAttribute("thisperson", person);
        return "new_person_page";
    }

    @PostMapping("/save")
    public String savePerson(Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "error";
        }

        personService.save(person);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String goToEditPage(@PathVariable Long id, Model model) {
        Optional<Person> person = personService.findPerson(id);
        if (person.isPresent()) {
            model.addAttribute("personToEdit", person.get());
            return "edit_person_page";
        }
        return "error";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.delete(id);
        return "redirect:/";
    }
}
