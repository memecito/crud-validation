package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.StudentInputDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoOnly;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    //INPUTS DTO
    @Mapping(target = "person", source = "personInputDto")
    Student toModel(StudentInputDto studentInputDto);

    Student update(@MappingTarget Student target, Student source);

    //OUPUTS DTO
    @Mappings({
            @Mapping(target = "personDtoFull", source = "person"),
            @Mapping(target = "teacherOutDtoOnly", source = "teacherStudent"),
            @Mapping(target = "subjectOutDtoMiniList",source = "subjectList")})
    StudentOutDtoFull toDtoFull(Student student);

    @Mappings({
            @Mapping(target = "subjectOutDtoMiniList", source = "subjectList"),
            @Mapping(target = "teacherOutDtoOnly", source = "teacherStudent")})
    StudentOutDtoMini toDtoMini(Student student);

    List<StudentOutDtoMini> toDtoListMini(List<Student> studentList);

    StudentOutDtoOnly toDtoOnly(Student studentById);
}
