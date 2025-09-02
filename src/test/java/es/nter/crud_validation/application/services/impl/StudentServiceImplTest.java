package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.DataProviders;
import es.nter.crud_validation.application.mappers.StudentMapper;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.infraestructure.repositories.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;
    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getAllStudent() {
        //Give
        List<Student> studentList= DataProviders.studentsListMock();
        Pageable pageable = PageRequest.of(0,5);
        Page<Student> studentPage= new PageImpl<>(studentList,pageable,studentList.size());
        //When
        when(studentRepository.findAll(pageable)).thenReturn(studentPage);
        List<Student> studentResoult= studentService.getAllStudent(0,5);
        //Then
        assertNotNull(studentResoult);
    }

    @Test
    void getStudentById() {
    }

    @Test
    void addStudent() {
    }

    @Test
    void addSubjects() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void deleteStudent() {
    }

    @Test
    void deleteSubjects() {
    }
}