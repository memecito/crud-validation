package es.nter.crud_validation.presentation.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutDtoOnly {
    private Long id;
    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};
}
