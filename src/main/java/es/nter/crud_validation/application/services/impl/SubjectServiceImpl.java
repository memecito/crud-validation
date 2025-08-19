package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.SubjectMapper;
import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.error.DeleteSubjectException;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Override
    public List<Subject> getAllSubject(int pageNumber, int pageSize) {

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return subjectRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public Subject getSubjectById(long id) {

        return subjectRepository.findById(id).orElseThrow(
                ()-> new EntityNotFoundException("subject", id)
        );
    }

    @Override
    public List<Subject> getSubjectByStudentId(long id){

        return Collections.singletonList(subjectRepository.findByStudentList_Id(id));
    }

    @Override
    public List<Subject> addSubject(List<Subject> subjectList) {

        return subjectRepository.saveAll(subjectList);
    }

    @Override
    public Subject updateSubject(long id, Subject subject) {

        return subjectMapper.update(getSubjectById(id),subject);
    }

    @Override
    public void deleteSubject(long id) {

       if(getSubjectById(id).getStudentList().isEmpty())
       {
           subjectRepository.deleteById(id);
       }else{
           throw  new DeleteSubjectException("Esta asignatura esta siendo cursada");

       }
    }
}
