package es.nter.crud_validation.presentation.dto.subject;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectOutDtoFull {
    private Long id;
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;
    private TeacherOutDtoMini teacherOutDtoMini;
    private List<StudentOutDtoFull> studentOutDtoFullList;
}
