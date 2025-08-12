package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.application.mappers.PersonMapper;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutDtoMini {
    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
    private PersonOutDtoMini personOutDtoMini;


}
