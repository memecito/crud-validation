package es.nter.crud_validation.application.services.impl;

import es.nter.crud_validation.application.mappers.TeacherMapper;
import es.nter.crud_validation.application.services.TeacherService;
import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.domain.models.Rol;
import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.exceptions.EntityNotFoundException;
import es.nter.crud_validation.exceptions.TeacherCreatedException;
import es.nter.crud_validation.infraestructure.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final PersonServiceImpl personService;

    @Override
    public List<Teacher> getAllTeacher(int pageNumber, int pageSize) {
        PageRequest pageRequest= PageRequest.of(pageNumber, pageSize);
        List<Teacher> teacherList=teacherRepository.findAll(pageRequest).getContent().stream().toList();
        return teacherList;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id)
                .orElseThrow(
                        ()->new EntityNotFoundException("profesor",id)
                );
    }

    @Override
    @Transactional
    public Teacher addTeacher(Teacher teacher) {
        Person p = personService.getPersonById(teacher.getPerson().getId());
            if(Objects.equals(p.getRol(), Rol.TEACHER)){
                throw new TeacherCreatedException("Esta persona ya tiene un rol aceptado");
            }else{
                p.setRol(Rol.TEACHER);
                p.setTeacher(teacher);
                teacher.addPerson(p);
                return teacherRepository.save(teacher);
            }
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Long id, Teacher teacher) {
        return teacherMapper.update(getTeacherById(id),teacher);
    }

    @Override
    @Transactional
    public void deleteTeacher(Long id) {
        Teacher teacher= getTeacherById(id);
            teacher.getPerson().setRol(Rol.NOBODY);
            teacher.getPerson().setTeacher(null);
            teacher.getStudentList().forEach(student -> student.setTeacherStudent(null));
            teacher.setStudentList(null);
    }
}
