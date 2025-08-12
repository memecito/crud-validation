package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.infraestructure.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Override
    public List<Subject> getAllSubject(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.findAll(pageRequest).getContent().stream().toList();
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
