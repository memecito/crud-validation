package es.nter.crud_validation.presentation.dto.subject;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutDtoMini {
    private Long id;
    private String subject;
    private Timestamp initialDate;

    private List<StudentOutDtoOnly> studentListOnly;

}
