package es.nter.crud_validation.presentation.dto.student;

import es.nter.crud_validation.domain.models.Branch;
import es.nter.crud_validation.presentation.dto.person.PersonInputDtoId;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInputDto {
    private Long id;
    @NotBlank(message = "Horas obligatorias")
    private int numHoursWeek;
    private String comments;
    @NotBlank(message = "Branch obligatorio")
    private Branch branch;

    @NotBlank(message = "Si no es persona como va a estudiar??")
    private PersonInputDtoId personInputDtoId;
}
