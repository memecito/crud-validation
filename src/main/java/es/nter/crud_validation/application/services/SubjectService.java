package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Subject;

import java.util.List;

public interface SubjectService {

    List<Subject> getAllSubject(int pageNumber, int pageSize);
    Subject getSubjectById( long id);
    Subject addSubject( Subject subject);
    Subject updateSubject(long id, Subject subject);
    void deleteSubject(long id);
}
