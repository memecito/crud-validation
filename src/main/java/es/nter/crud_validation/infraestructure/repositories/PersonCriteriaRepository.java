package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface PersonCriteriaRepository extends JpaRepository<Person, Long> {

    List<Person> findPersonByCustomParam(Map<String, String> params);
}
