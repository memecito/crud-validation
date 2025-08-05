package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import lombok.*;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "persons")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String city;


}
