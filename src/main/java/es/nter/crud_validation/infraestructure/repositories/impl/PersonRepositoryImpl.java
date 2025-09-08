package es.nter.crud_validation.infraestructure.repositories.impl;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.infraestructure.repositories.PersonCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public abstract class PersonRepositoryImpl implements PersonCriteriaRepository {

    EntityManager em;

    @Override
    public List<Person> findPersonByCustomParam(Map<String, String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();
        for (String key : params.keySet()) {
            switch (key) {
                case "user":
                    if (!key.isEmpty() && key != null) {
                        predicates.add(cb.like(personRoot.get(key), "%%" + params.get(key) + "%"));
                    }
                case "name":
                    if (!key.isEmpty() && key != null) {
                        predicates.add(cb.like(personRoot.get(key), "%%" + params.get(key) + "%"));
                    }
                    break;
                case "surname":
                    if (key.isEmpty() && key != null) {
                        predicates.add(cb.like(personRoot.get(key), "%%" + params.get(key) + "%"));
                    }
                    break;
                case "createdDateFrom":
                    if (key.isEmpty() && key != null) {
                        predicates.add(cb.like(personRoot.get(key), "%%" + params.get(key) + "%"));
                    }
                    break;
                case "createdDateTo":
                    if (key.isEmpty() && key != null) {
                        predicates.add(cb.like(personRoot.get(key), "%%" + params.get(key) + "%"));
                    }
                    break;
                case "oderBy":
                    if (key.isEmpty() && key != null) {
                        criteriaQuery.orderBy(cb.asc(personRoot.get(params.get(key))));
                    }
                    break;
                default:
                    break;
            }
        }
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        return em.createQuery(criteriaQuery).getResultList();
    }
}
