package es.nter.crud_validation.presentation.dto;

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
public class PersonOutDtoMini {

    private Long id;
    private String username;

    private String name;
    private String companyEmail;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
}
