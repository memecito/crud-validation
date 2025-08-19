package es.nter.crud_validation.domain.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "persons")
public class Person {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;
    private String surname;
    @Column(nullable = false)
    private String personalEmail;
    @Column(nullable = false)
    private String companyEmail;
    @Column(nullable = false)
    private String city;
    private Boolean active;
    @Column(nullable = false)
    private Timestamp createdDate;
    private String imageUrl;
    private Timestamp terminationDate;
    @Enumerated(EnumType.STRING)
    private Rol rol= Rol.NOBODY;

    //RELACIONES
    @JsonManagedReference
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @JsonManagedReference
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;


}
