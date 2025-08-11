package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import lombok.*;

import java.util.Date;

@Getter
@Setter
public class PersonOutDtoMini {

    private Long id;
    private String username;

    private String name;
    private String companyEmail;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;

    private StudentOutDtoMini studentOutDtoMini;

    private TeacherOutDtoMini teacherOutDtoMini;
}
