package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.infraestructure.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageRequest).getContent().stream().toList();
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
