package es.nter.crud_validation.presentation.dto.teacher;

import es.nter.crud_validation.presentation.dto.person.PersonDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutDtoPerson {

    private PersonDto personDto;

    private Long id;
    private String comments;
    private String branch;

    private List<StudentOutDtoMini> studentOutDtoMiniList;
}
