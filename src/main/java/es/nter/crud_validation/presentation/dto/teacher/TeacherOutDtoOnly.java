package es.nter.crud_validation.presentation.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherOutDtoOnly {
    private Long id;
    private String comments;
    private String branch;
}
