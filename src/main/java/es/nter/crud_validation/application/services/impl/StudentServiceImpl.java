package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Student;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    @Override
    public List<Student> getAllStudent(int pageNumber, int pageSize) {
        return List.of();
    }

    @Override
    public Student getStudentById(long id) {
        return null;
    }

    @Override
    public Student addStudent(Student student) {
        return null;
    }

    @Override
    public Student updateStudent(long id, Student student) {
        return null;
    }

    @Override
    public void deleteStudent(long id) {

    }
}
