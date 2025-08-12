package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.presentation.dto.person.PersonDtoFull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutDtoFull {
    private Long id;

    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
    private PersonDtoFull personDtoFull;

}
