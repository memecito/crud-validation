package es.nter.crud_validation.presentation.dto.teacher;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.nter.crud_validation.presentation.dto.person.PersonDtoFull;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class TeacherOutDtoFull {

    private Long id;
    private String comments;
    private String branch;

    private PersonDtoFull personDtoFull;

    private List<StudentOutDtoOnly> studentOutDtoOnlyList;
}
