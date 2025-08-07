package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String city;
    private Boolean active;
    private Timestamp createdDate;
    private String imageUrl;
    private Timestamp terminationDate;
}
