package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoTeacherSubjects;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutDtoStudent {

    private Long id;
    private String username;
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

    private StudentOutDtoTeacherSubjects studentOutDtoTeacherSubjects;

}
