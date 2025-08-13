package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher(int pageNumber, int pageSize);
    Teacher getTeacherById(Long id);
    Teacher addTeacher(Teacher teacher);
    Teacher updateTeacher(Long id, Teacher teacher);
    void deleteTeacher(Long id);
}
