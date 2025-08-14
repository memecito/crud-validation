package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.presentation.dto.person.PersonDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutDtoFull {
    private Long id;

    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
    private PersonDtoFull personDtoFull;
    private TeacherOutDtoOnly teacherOutDtoOnly;
    private List<SubjectOutDtoMini> subjectOutDtoMiniList;

}
