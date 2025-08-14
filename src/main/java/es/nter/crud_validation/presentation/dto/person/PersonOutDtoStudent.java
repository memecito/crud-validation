package es.nter.crud_validation.presentation.dto.person;

import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
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
    private String companyEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    //private StudentOutDtoOnly studentOutDtoOnly;
    private StudentOutDtoMini studentOutDtoMini;

}
