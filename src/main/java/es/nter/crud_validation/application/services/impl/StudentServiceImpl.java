package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.StudentMapper;
import es.nter.crud_validation.application.services.StudentService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.error.EntityNotFoundException;
import es.nter.crud_validation.error.StudentCreatedException;
import es.nter.crud_validation.infraestructure.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final SubjectServiceImpl subjectService;
    private final PersonServiceImpl personService;

    @Override
    public List<Student> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);

        return studentRepository.findAll(pageRequest).getContent().stream().toList();
    }

    @Override
    public Student getStudentById(long id) {
        return studentRepository.findById(id)
                .orElseThrow(
                        ()-> new EntityNotFoundException("estudiante",id)
                );
    }

    @Override
    @Transactional
    public Student addStudent(Student student) {

        Person p= personService.getPersonById(student.getPerson().getId());
        if(Objects.equals(p.getRol(), Rol.TEACHER))
        {
            throw new StudentCreatedException("Esta persona ya tiene un rol asignado");
        }else
        {
            p.setRol(Rol.STUDENT);
            p.setStudent(student);
            student.addPerson(p);
            return studentRepository.save(student);
        }
    }

    @Override
    @Transactional
    public Student addSubjects(long id, List<Subject> subjectList) {
        Student student= getStudentById(id);
        for (Subject s: subjectList){
            Subject subject= subjectService.getSubjectById(s.getId());
            if (!student.getSubjectList().contains(subject)){
                student.addSubject(subject);
            }
        }
        return studentRepository.save(student);
    }

    @Override
    @Transactional
    public Student updateStudent(long id, Student student) {
        return studentMapper.update(getStudentById(id),student);
    }

    @Override
    @Transactional
    public void deleteStudent(long id) {
        Student student= getStudentById(id);
        if(student.getPerson()!=null){
            student.getPerson().setRol(Rol.NOBODY);
            student.getPerson().setStudent(null);
        }
    }

    @Override
    public Student deleteSubjects(long id, List<Subject> subjectList) {
        Student student= getStudentById(id);
        for (Subject s: subjectList){
            Subject subject= subjectService.getSubjectById(s.getId());
            if (student.getSubjectList().contains(subject)){
                student.removeSubject(subject);
            }
        }
        return studentRepository.save(student);
    }
}
