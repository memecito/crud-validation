package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="subject")
public class Subject {

    //ManyToMany con student
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String comments;

    private Timestamp initialDate;

    private Timestamp finishDate;


    @ManyToMany
    @JoinTable(name = "student_subject",
        joinColumns = @JoinColumn(name="subject_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentList;



}
