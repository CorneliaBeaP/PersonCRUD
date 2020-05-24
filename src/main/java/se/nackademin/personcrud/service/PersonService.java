package se.nackademin.personcrud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.nackademin.personcrud.domain.Person;
import se.nackademin.personcrud.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public void save(Person person) {
        personRepository.save(person);
    }

    @Transactional
    public void delete(long id) {
        personRepository.deleteById(id);
    }

    public List<Person> listAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Person> findPerson(long id) {
        return personRepository.findById(id);
    }
}
