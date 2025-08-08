package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
