package es.nter.crud_validation.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
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
    private String comments;
    private Timestamp initialDate;
    private Timestamp finishDate;

}
