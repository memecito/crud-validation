package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="subject")
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String subject;
    private String comments;
    @Column(nullable = false)
    private Timestamp initialDate;
    private Timestamp finishDate;

    //RELACIONES
    @ManyToMany(mappedBy = "subjectList", fetch = FetchType.LAZY)
    private List<Student> studentList= new ArrayList<>();



}
