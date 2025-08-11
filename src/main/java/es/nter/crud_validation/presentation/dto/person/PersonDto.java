package es.nter.crud_validation.presentation.dto.person;

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

    @Length(min=6, max = 10, message = "El nombre debe tener entre 6 y 10 caracteres")
    @NotBlank(message = "Usuario obligatorio")
    private String username;
    @NotBlank(message = "Contraseña obligatorio")
    private String password;
    @NotBlank(message = "Nombre obligatorio")
    private String name;

    private String surname;
    @NotBlank(message = "Email obligatorio")
    private String companyEmail;
    @NotBlank(message = "Ciudad obligatorio")
    private String city;
    @NotNull(message = "Estado obligatorio")
    private Boolean active;
    @NotNull(message = "Fecha de creacion obligatorio")
    private Date createdDate;

    private String imageUrl;
    private Date terminationDate;
}
