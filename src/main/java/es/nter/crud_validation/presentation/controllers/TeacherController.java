package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.presentation.dto.teacher.TeacherInputDto;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoFull;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teachers")
@RequiredArgsConstructor
public class TeacherController {

        private final TeacherService teacherService;
        private final TeacherMapper teacherMapper;

        @GetMapping
        public ResponseEntity<List<TeacherOutDtoMini>> getAllTeacher(
                @RequestParam(defaultValue = "0", required = false) int pageNumber,
                @RequestParam (defaultValue = "5", required = false) int pageSize){
            return ResponseEntity.ok(
                    teacherService.getAllTeacher(pageNumber, pageSize)
                            .stream().map(teacherMapper::toDtoMini)
                            .collect(Collectors.toList())
            );
        }

    @GetMapping("/sindto")
    public ResponseEntity<List<Teacher>> getAllTeacherSinDto(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize){
        return ResponseEntity.ok(
                teacherService.getAllTeacher(pageNumber, pageSize)
        );
    }

        @GetMapping("/{id}")
        public ResponseEntity<TeacherOutDtoFull> getTeacherById(
                @PathVariable Long id){
            return ResponseEntity.ok(teacherMapper.toDtoFull(teacherService.getTeacherById(id)));
        }

        @PostMapping
        public ResponseEntity<TeacherOutDtoFull> createTeacher(
                @Valid @RequestBody TeacherInputDto teacherInputDto){
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    teacherMapper.toDtoFull(
                            teacherService.addTeacher(
                                    teacherMapper.toModel(teacherInputDto)
                            )
                    )
            );
        }

        @PutMapping("/{id}")
        public ResponseEntity<TeacherOutDtoMini> updateTeacher(
                @PathVariable long id,
                @RequestBody TeacherInputDto teacherInputDto){
            return ResponseEntity.ok(
                    teacherMapper.toDtoMini(
                            teacherService.updateTeacher(
                                    id, teacherMapper.toModel(teacherInputDto)
                            )
                    )
            );
        }

        @DeleteMapping("/id")
        public ResponseEntity deleteTeacher(@PathVariable long id){
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok().build();
        }

}
