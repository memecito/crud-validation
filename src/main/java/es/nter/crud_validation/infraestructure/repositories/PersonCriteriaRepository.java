package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface PersonCriteriaRepository {

    Page<Person> findPersonByCustomParam(Map<String, String> params, Pageable  pageable);

    }
