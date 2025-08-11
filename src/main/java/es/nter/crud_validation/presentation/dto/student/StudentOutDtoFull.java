package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.presentation.dto.person.PersonDtoFull;

public class StudentOutDtoFull {
    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
    private PersonDtoFull personDtoFull;

}
