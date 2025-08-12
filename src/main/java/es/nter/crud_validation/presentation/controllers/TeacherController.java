package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
