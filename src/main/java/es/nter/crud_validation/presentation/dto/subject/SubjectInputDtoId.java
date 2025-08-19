package es.nter.crud_validation.presentation.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInputDtoId {
    private Long id;

    private String subject;
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;
}
