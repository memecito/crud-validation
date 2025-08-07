package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Person;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByCity(String city);

    Optional<Person> findByName(String name);

    List<Person> findByActiveTrue(PageRequest pageRequest);
}
