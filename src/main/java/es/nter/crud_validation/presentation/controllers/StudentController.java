package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.StudentMapper;
import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.StudentInputDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;


    @GetMapping
    public ResponseEntity<List<StudentOutDtoMini>> getAllStudent(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize) {

        return ResponseEntity.ok(
               studentService.getAllStudent(pageNumber, pageSize)
                       .stream().map(studentMapper::toDtoMini)
                       .collect(Collectors.toList()));
    }
    @GetMapping("/2")
    public ResponseEntity<List<Student>> getAllStudent2(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize) {

        return ResponseEntity.ok(
                studentService.getAllStudent(pageNumber, pageSize)
                        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutDtoFull> getStudentId(Long id){
        return ResponseEntity.ok(
                studentMapper.toDtoFull(
                studentService.getStudentById(id)));
    }

    @PostMapping
    public ResponseEntity<StudentOutDtoFull> addStudent(StudentInputDto studentInputDto){
        return ResponseEntity.ok(
                studentMapper.toDtoFull(
                        studentService.addStudent(
                                studentMapper.toModel(studentInputDto)
                        )
                )
        );
    }


}
