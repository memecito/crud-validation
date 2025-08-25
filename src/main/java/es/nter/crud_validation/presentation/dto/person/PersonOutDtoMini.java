package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class PersonOutDtoMini {

    private Long id;
    private String username;
    private String personalEmail;
    private String companyEmail;
    private Rol rol;
    private Date createdDate;


}
