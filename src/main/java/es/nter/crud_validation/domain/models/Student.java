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
@Table(name="student")
public class Student {
    //OneToOne con person
    //ManytoOne con teacher

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numHoursWeek;
    private String comments;
    private enum branch {Front, Back, FullStack};


}
