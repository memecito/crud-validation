package es.nter.crud_validation.application.services;

import es.nter.crud_validation.domain.models.Student;

import java.util.List;

public interface StudentService {

    List<Student> getAllStudent (int pageNumber, int pageSize);

    Student getStudentById(long id);

    Student addStudent( Student student);

    Student updateStudent(long id, Student student);

    void deleteStudent(long id);
}
