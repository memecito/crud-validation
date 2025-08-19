package es.nter.crud_validation.presentation.dto.subject;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutDtoOnly {
    private Long id;
    private String subject;
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;
}
