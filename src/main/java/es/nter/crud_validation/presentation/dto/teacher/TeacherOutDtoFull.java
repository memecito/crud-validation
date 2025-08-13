package es.nter.crud_validation.presentation.dto.teacher;

import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutDtoFull {
    private Long id;
    private String comments;
    private String branch;
    private PersonOutDtoMini personMini;
    private List<StudentOutDtoMini> studentOutDtoMiniList;

}
