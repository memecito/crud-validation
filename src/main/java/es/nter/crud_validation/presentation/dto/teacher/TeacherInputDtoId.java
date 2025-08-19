package es.nter.crud_validation.presentation.dto.teacher;

import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInputDtoId {
    private Long id;

    private String comments;
    private String branch;
    private PersonInputDto personInputDto;

}
