package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    @Override
    public List<Teacher> getAllTeacher(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(
                        ()->new EntityNotFoundException("profesor",id)
                );
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher updateTeacher(Long id, Teacher teacher) {
        return null;
    }

    @Override
    public void deleteTeacher(Long id) {

    }
}
