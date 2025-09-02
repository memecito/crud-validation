package es.nter.crud_validation.presentation.dto.person;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

@Getter
@Setter
public class PersonInputDtoId {
    private Long id;
    @Length(min=6, max = 10, message = "El nombre debe tener entre 6 y 10 caracteres")
    @NotBlank(message = "Usuario obligatorio")
    private String username;
    @NotBlank(message = "Contraseña obligatoria")
    private String password;
    @NotBlank(message = "Nombre obligatorio")
    private String name;

    private String surname;

    private String personalEmail;

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
