package es.nter.crud_validation.infraestructure.repositories.impl;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.infraestructure.repositories.PersonCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Person> findPersonByCustomParam(Map<String, String> params, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // --- 1. Preparación de la CONSULTA DE DATOS ---
        CriteriaQuery<Person> dataQuery = cb.createQuery(Person.class);
        Root<Person> dataRoot = dataQuery.from(Person.class);
        List<Predicate> dataPredicates = new ArrayList<>();

        // --- 2. Preparación de la CONSULTA DE CONTEO ---
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Person> countRoot = countQuery.from(Person.class); // <-- El FROM para la consulta de conteo
        List<Predicate> countPredicates = new ArrayList<>();

        // --- 3. Lógica para construir los PREDICADOS (se aplica a ambas consultas) ---
        params.forEach((key, value) -> {
            if (value == null || value.isEmpty() || key.equals("orderBy")) {
                return; // Ignora valores vacíos y la clave de ordenación
            }
            switch (key) {
                case "username":
                case "name":
                case "surname":
                    dataPredicates.add(cb.like(dataRoot.get(key), "%" + value + "%"));
                    countPredicates.add(cb.like(countRoot.get(key), "%" + value + "%"));
                    break;
                case "createdDateFrom":
                    LocalDate fromDate = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
                    dataPredicates.add(cb.greaterThanOrEqualTo(dataRoot.get("createdDate"), fromDate));
                    countPredicates.add(cb.greaterThanOrEqualTo(countRoot.get("createdDate"), fromDate));
                    break;
                case "createdDateTo":
                    LocalDate toDate = LocalDate.parse(value, DateTimeFormatter.ISO_LOCAL_DATE);
                    dataPredicates.add(cb.lessThanOrEqualTo(dataRoot.get("createdDate"), toDate));
                    countPredicates.add(cb.lessThanOrEqualTo(countRoot.get("createdDate"), toDate));
                    break;
            }
        });

        // --- 4. Ejecutar la CONSULTA DE CONTEO ---
        countQuery.select(cb.count(countRoot)).where(countPredicates.toArray(new Predicate[0]));
        Long total = em.createQuery(countQuery).getSingleResult();

        // Si no hay resultados, no es necesario ejecutar la consulta de datos
        if (total == 0) {
            return new PageImpl<>(new ArrayList<>(), pageable, 0);
        }

        // --- 5. Ejecutar la CONSULTA DE DATOS ---
        dataQuery.where(dataPredicates.toArray(new Predicate[0]));

        String orderByField = params.get("orderBy");
        if (orderByField != null && !orderByField.isEmpty()) {
            dataQuery.orderBy(cb.asc(dataRoot.get(orderByField)));
        }

        List<Person> personList = em.createQuery(dataQuery)
                .setFirstResult((int) pageable.getOffset())
                .setMaxResults(pageable.getPageSize())
                .getResultList();

        // --- 6. Devolver el resultado ---
        return new PageImpl<>(personList, pageable, total);
    }


}
