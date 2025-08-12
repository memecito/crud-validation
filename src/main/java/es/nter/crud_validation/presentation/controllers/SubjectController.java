package es.nter.crud_validation.presentation.controllers;

import es.nter.crud_validation.application.services.SubjectService;
import es.nter.crud_validation.domain.models.Subject;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/subjects")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;



    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubject(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam (defaultValue = "5", required = false) int pageSize) {
        return ResponseEntity.ok(
                new ArrayList<>(subjectService.getAllSubject(pageNumber, pageSize)));
    }
}
