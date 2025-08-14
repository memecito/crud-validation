package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.SubjectMapper;
import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoMini;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoOnly;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;
    private final SubjectMapper subjectMapper;



    @GetMapping
    public ResponseEntity<List<SubjectOutDtoMini>> getAllSubject(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize) {
        return ResponseEntity.ok(
                subjectService.getAllSubject(pageNumber, pageSize)
                        .stream().map(subjectMapper::toDtoMini).collect(Collectors.toList())        );
    }
    @GetMapping("/sindto")
    public ResponseEntity<List<Subject>> getAllSubjectSinDto(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize) {
        return ResponseEntity.ok(
                subjectService.getAllSubject(pageNumber, pageSize)
                               );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectOutDtoFull> getSubjectById(
            @PathVariable Long id)  {
        return ResponseEntity.ok(
                subjectMapper.toDtoFull(
                subjectService.getSubjectById(id)));
    }

    @GetMapping("/student")
    public ResponseEntity<List<SubjectOutDtoOnly>> getSubjectStudentId(
            @RequestParam Long studentId
    ){
        return ResponseEntity.ok(subjectService.getSubjectByStudentId(studentId)
                .stream().map(subjectMapper::toDtoOnly).collect(Collectors.toList()));
    }
}
