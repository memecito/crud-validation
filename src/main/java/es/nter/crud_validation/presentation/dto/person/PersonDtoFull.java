package es.nter.crud_validation.presentation.dto.person;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.nter.crud_validation.domain.models.Rol;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDtoFull {
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

}
