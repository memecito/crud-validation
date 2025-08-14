package es.nter.crud_validation.infraestructure.repositories;

import es.nter.crud_validation.domain.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findByStudentList_Id(Long id);
}
