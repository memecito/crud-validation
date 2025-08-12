package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.StudentMapper;
import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.infraestructure.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public List<Student> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("estudiante",id)
                );
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {
        return studentRepository.save(getStudentById(student.getId()));
    }

    @Override
    public Student updateStudent(long id, Student student) {
        return studentMapper.update(getStudentById(id),student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.delete(getStudentById(id));

    }
}
