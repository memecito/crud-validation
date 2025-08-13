package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.StudentInputDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentOutDtoFull toDtoFull(Student student);

    @Mappings({
            @Mapping(target = "personOutDtoMini", source = "person"),
            @Mapping(target = "teacherOutDtoMini", source = "teacherStudent")
    })
    StudentOutDtoMini toDtoMini(Student student);


    List<StudentOutDtoMini> toDtoListMini(List<Student> studentList);
    Student toModel(StudentInputDto studentInputDto);

    Student update(@MappingTarget Student target, Student source);
}
