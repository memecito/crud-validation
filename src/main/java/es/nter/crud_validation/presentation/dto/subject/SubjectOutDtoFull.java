package es.nter.crud_validation.presentation.dto.subject;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoSubjects;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class SubjectOutDtoFull {
    private Long id;
    private String subject;
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;

    private List<StudentOutDtoOnly> studentOutDtoOnlyList;
}
