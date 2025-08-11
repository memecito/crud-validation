package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
