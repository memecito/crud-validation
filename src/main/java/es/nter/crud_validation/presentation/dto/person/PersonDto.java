package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoOnly;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String personalEmail;

    private String companyEmail;
    private Rol rol;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

}
