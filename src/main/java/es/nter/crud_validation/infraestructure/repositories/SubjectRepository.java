package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
