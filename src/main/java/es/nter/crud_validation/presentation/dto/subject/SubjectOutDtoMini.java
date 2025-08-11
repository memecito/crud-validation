package es.nter.crud_validation.presentation.dto.subject;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;

import java.sql.Timestamp;

public class SubjectOutDtoMini {
    private Long id;
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;
    private StudentOutDtoMini studentMini;
}
