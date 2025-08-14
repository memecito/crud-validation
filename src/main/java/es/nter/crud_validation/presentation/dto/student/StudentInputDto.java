package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    @NotBlank(message = "Horas obligatorias")
    private int numHoursWeek;
    private String comments;
    @NotBlank(message = "Branch obligatorio")
    private enum branch {Front, Back, FullStack};
    @NotBlank(message = "Si no es persona como va a estudiar??")
    private PersonInputDto personInputDto;
}
