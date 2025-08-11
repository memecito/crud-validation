package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Teacher;

import java.util.List;

public interface TeacherService {
    List<Teacher> getAllTeacher(int pageNumber, int pageSize);
    Teacher getTeacherById(long id);
    Teacher addTeacher(Teacher teacher);
    Teacher updateTeacher(long id, Teacher teacher);
    void deleteTeacher(long id);
}
