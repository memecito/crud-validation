package es.nter.crud_validation.infraestructure.repositories.impl;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.infraestructure.repositories.PersonCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class PersonRepositoryImpl implements PersonCriteriaRepository {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Person> findPersonByCustomParam(Map<String, String> params) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Person> criteriaQuery = cb.createQuery(Person.class);
        Root<Person> personRoot = criteriaQuery.from(Person.class);

        List<Predicate> predicates = new ArrayList<>();


        String orderByField= params.get("orderBy");
        if(orderByField!=null && !orderByField.isEmpty()){
            criteriaQuery.orderBy(cb.asc(personRoot.get(orderByField)));
        }

        params.forEach((key,value)->{
            if(value==null || value.isEmpty()){
                return;
            }
            switch (key){
                case "username":
                case "name":
                case "surname":
                    predicates.add(cb.like(personRoot.get(key), "%"+value+"%"));
                    break;
                case "createdDateFrom":
                    LocalDate fromDate= LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
                    predicates.add(cb.greaterThanOrEqualTo(personRoot.get("createdDate"), fromDate));
                    break;
                case "createdDateTo":
                    LocalDate toDate= LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
                    predicates.add(cb.lessThanOrEqualTo(personRoot.get("createdDate"), toDate));
                    break;

            }
        });
        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Person> objectsList= em.createQuery(criteriaQuery).getResultList();
        return objectsList;
    }
}
