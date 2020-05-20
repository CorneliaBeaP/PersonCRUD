package se.nackademin.personcrud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import se.nackademin.personcrud.domain.Person;
import se.nackademin.personcrud.repository.PersonRepository;

@SpringBootApplication
public class PersoncrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersoncrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner setUp(PersonRepository personRepository){
        return (args -> {
            if(personRepository.findAll().size()==0) {
                personRepository.save(new Person("Maria", "Axelsson", "Örsvägen 4", 17450, "Sundbyberg", "m.axelsson@gmail.com", "F", 1993));
                personRepository.save(new Person("Ceasar", "Sandberg", "Granhammarsvägen", 19637, "Kungsängen", "ceasar@sandbergfamily.se", "M", 2001));
                personRepository.save(new Person("Viola", "Andersson", "Skedevivägen", 64394, "Vingåker", "viola135@hotmail.com", "F", 1989));
                personRepository.save(new Person("Åke", "Strömberg", "Torggatan 6", 44730, "Vårgårda", "akeake@live.se", "M", 1997));
                personRepository.save(new Person("Astrid", "Blom", "Kyrkogatan 18", 79230, "Mora", "astridblom@gmail.com", "F", 1993));
                personRepository.save(new Person("Franz", "Falk", "Västlundavägen 37", 93332, "Arvidsjaur", "franzzzzy@hotmail.com", "M", 1999));
                personRepository.save(new Person("Rakel", "Nyström", "Ormvråksgatan 40", 55612, "Jönköping", "rakel.nystrom@gmail.com", "F", 2000));
            }});
    }
}
