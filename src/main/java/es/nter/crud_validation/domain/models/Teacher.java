package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "teacher")
public class Teacher {

    //OnetoOne con person

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comments;
    private String branch;

    /*
    @OneToOne
    @JoinColumn(name="person_id", referencedColumnName = "id")
    private Person person;

     */
}
