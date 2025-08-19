package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoOnly;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoStudents;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutDtoTeacher {

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

   private TeacherOutDtoStudents teacherOutDtoStudents;

}
