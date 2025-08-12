package es.nter.crud_validation.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

    @JsonBackReference
    @OneToOne (mappedBy = "teacher")
    private Person person;

    @JsonBackReference
    @OneToMany(mappedBy = "teacherStudent")
    private List<Student> studentList;

}
