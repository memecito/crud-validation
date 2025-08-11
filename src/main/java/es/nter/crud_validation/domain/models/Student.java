package es.nter.crud_validation.domain.models;

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
    /*
    @OneToOne
    @JoinColumn(name="person", referencedColumnName = "id")
    private Person person;
    */


    @ManyToOne
    @JoinColumn(name="id_teacher", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(mappedBy = "studentList")
    List<Subject> subjectList;



}
