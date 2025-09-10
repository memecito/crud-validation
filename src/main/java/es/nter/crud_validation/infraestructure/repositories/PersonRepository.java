package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    List<Person> findByCity(String city);

    Optional<Person> findByName(String name);

    Optional<Person> findByUsername(String userName);

    List<Person> findByActiveTrue(PageRequest pageRequest);

    List<Person> findByRolIs(Rol rol, PageRequest pageRequest);

    Optional<Person> findByUsernameIs(String username);

    boolean existsByUsername(String username);
}
