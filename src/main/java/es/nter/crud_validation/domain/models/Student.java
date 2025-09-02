package es.nter.crud_validation.domain.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name="students ")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private int numHoursWeek;
    private String comments;
    @Enumerated(EnumType.STRING)
    private Branch branch;

    //RELACIONES
    @JsonBackReference
    @OneToOne(mappedBy = "student")
    private Person person;


    @JsonManagedReference
    @ManyToOne( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="id_teacher", referencedColumnName = "id")
    private Teacher teacherStudent;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "student_subject",
            joinColumns =  @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name="subject_id"))
    private List<Subject> subjectList= new ArrayList<>();

    public Student(long id, int numHoursWeek, String comments, Branch branch) {
        this.id=id;
        this.numHoursWeek=numHoursWeek;
        this.comments=comments;
        this.branch=branch;
    }


    //METODOS
    public void addPerson(Person person){
        this.person=person;
    }

    public void addSubject(Subject subject){
        this.subjectList.add(subject);
        subject.getStudentList().add(this);
    }

    public void removeSubject(Subject subject){
        this.subjectList.remove(subject);
        subject.getStudentList().remove(this);
    }



}
