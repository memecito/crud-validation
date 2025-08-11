package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;

public class StudentOutDtoMini {
    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
    private PersonOutDtoMini personMini;
}
