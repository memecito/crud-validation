package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.domain.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {
    @Override
    public List<Teacher> getAllTeacher(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public Teacher getTeacherById(long id) {
        return null;
    }

    @Override
    public Teacher addTeacher(Teacher teacher) {
        return null;
    }

    @Override
    public Teacher updateTeacher(long id, Teacher teacher) {
        return null;
    }

    @Override
    public void deleteTeacher(long id) {

    }
}
