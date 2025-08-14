package es.nter.crud_validation.presentation.dto.subject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInputDto {
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;
}
