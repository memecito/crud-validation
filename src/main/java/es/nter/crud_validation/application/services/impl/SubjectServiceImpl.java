package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    @Override
    public List<Subject> getAllSubject(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public Subject getSubjectById(long id) {
        return null;
    }

    @Override
    public Subject addSubject(Subject subject) {
        return null;
    }

    @Override
    public Subject updateSubject(long id, Subject subject) {
        return null;
    }

    @Override
    public void deleteSubject(long id) {

    }
}
