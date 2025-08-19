package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.StudentMapper;
import es.nter.crud_validation.application.mappers.SubjectMapper;
import es.nter.crud_validation.application.services.PersonService;
import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.*;
import es.nter.crud_validation.presentation.dto.subject.SubjectInputDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;
    private final SubjectMapper subjectMapper;


    @GetMapping
    public ResponseEntity<List<StudentOutDtoMini>> getAllStudent(
            @RequestParam (defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "10", required = false) int pageSize) {

        return ResponseEntity.ok(
               studentService.getAllStudent(pageNumber, pageSize)
                       .stream().map(studentMapper::toDtoMini)
                       .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentId(
            @PathVariable Long id,
            @RequestParam (defaultValue = "simple",required = false) String ouputType){

        return switch (ouputType) {
            case "full" -> ResponseEntity.ok(
                    studentMapper.toDtoFull(
                            studentService.getStudentById(id)));
            case "simple" -> ResponseEntity.ok(
                    studentMapper.toDtoOnly(
                            studentService.getStudentById(id)));
            default -> throw new IllegalStateException("Unexpected value: " + ouputType);
        };
    }

    @GetMapping("/{id}/subjects")
    public ResponseEntity<StudentOutDtoSubjects> getStudentSubjects(
            @PathVariable long id){

        return ResponseEntity.ok(
                studentMapper.toDtoSubjects(studentService.getStudentById(id))
        );
    }

    @PostMapping
    public ResponseEntity<StudentOutDtoFull> addStudent(@Valid @RequestBody  StudentInputDto studentInputDto){

        return ResponseEntity.ok(
                studentMapper.toDtoFull(
                        studentService.addStudent(
                                studentMapper.toModel(studentInputDto)
                        )
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutDtoMini> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentInputDto studentInputDto){

        return  ResponseEntity.ok(
                studentMapper.toDtoMini(
                        studentService.updateStudent(id,
                                studentMapper.toModel(studentInputDto))
                )
        );
    }

    @PutMapping("/{id}/subjects")
    public ResponseEntity<StudentOutDtoFull> updateStudentSubject(
            @PathVariable Long id,
            @RequestBody List<SubjectInputDto> subjectInputDtoList){

        return ResponseEntity.ok(
                studentMapper.toDtoFull(
                        studentService.addSubjects(id,
                                subjectInputDtoList.stream()
                                        .map(subjectMapper::toModel).toList())
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteStudent(@PathVariable Long id){

        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/subjects")
    public ResponseEntity<StudentOutDtoFull> deleteStudentSubjects(
            @PathVariable Long id,
            @RequestBody List<SubjectInputDto> subjectInputDtoList){

        return ResponseEntity.ok(
                studentMapper.toDtoFull(
                        studentService.deleteSubjects(id,
                                subjectInputDtoList.stream()
                                        .map(subjectMapper::toModel).toList())
                )
        );
    }


}
