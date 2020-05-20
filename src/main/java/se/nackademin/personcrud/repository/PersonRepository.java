package se.nackademin.personcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.nackademin.personcrud.domain.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
