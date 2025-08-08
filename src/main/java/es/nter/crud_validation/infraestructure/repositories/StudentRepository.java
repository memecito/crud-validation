package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
