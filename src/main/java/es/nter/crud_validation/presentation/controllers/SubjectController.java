package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.SubjectMapper;
import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.presentation.dto.subject.SubjectInputDto;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoMini;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoOnly;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
            @RequestParam (defaultValue = "10", required = false) int pageSize) {

        return ResponseEntity.ok(
                subjectService.getAllSubject(pageNumber, pageSize)
                        .stream().map(subjectMapper::toDtoMini).collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectOutDtoFull> getSubjectById(
            @PathVariable Long id)  {

        return ResponseEntity.ok(
                subjectMapper.toDtoFull(
                subjectService.getSubjectById(id))
        );
    }

    @GetMapping("/student")
    public ResponseEntity<List<SubjectOutDtoOnly>> getSubjectStudentId(
            @RequestParam Long studentId){

        return ResponseEntity.ok(subjectService.getSubjectByStudentId(studentId)
                .stream().map(subjectMapper::toDtoOnly).collect(Collectors.toList())
        );
    }

    @PostMapping
    public ResponseEntity<List<SubjectOutDtoFull>> createdSubject (
            @RequestBody List<SubjectInputDto> subjectInputDto){

        return ResponseEntity.ok(
                       subjectService.addSubject(
                               subjectInputDto.stream().map(subjectMapper::toModel)
                                       .collect(Collectors.toList())
                       ).stream().map(subjectMapper::toDtoFull).collect(Collectors.toList())
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubjectOutDtoFull> updateSubject(
            @PathVariable Long id,
            @RequestParam SubjectInputDto subjectInputDto){

        return ResponseEntity.ok(
                subjectMapper.toDtoFull(
                        subjectService.updateSubject(id,
                                subjectMapper.toModel(subjectInputDto))
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletedSubject(@PathVariable Long id){

        subjectService.deleteSubject(id);
        return ResponseEntity.ok().body("Subject eliminada");
    }
}
