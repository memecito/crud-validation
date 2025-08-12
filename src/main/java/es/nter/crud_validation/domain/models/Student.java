package es.nter.crud_validation.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="students ")
public class Student {
    //OneToOne con person
    //ManytoOne con teacher

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numHoursWeek;

    private String comments;

    @Enumerated(EnumType.STRING)
    private Branch branch;

    //RELACIONES
    @JsonBackReference
    @OneToOne(mappedBy = "student")
    private Person person;


    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="id_teacher", referencedColumnName = "id")
    private Teacher teacherStudent;




}
