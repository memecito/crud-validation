package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubject(int pageNumber, int pageSize);
    Subject getSubjectById( long id);
    List<Subject> getSubjectByStudentId( long id);
    Subject addSubject( Subject subject);
    Subject updateSubject(long id, Subject subject);
    void deleteSubject(long id);
}
