package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.*;
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
    @Mapping(target = "person", source = "personInputDtoId")
    Student toModel(StudentInputDto studentInputDto);

    Student update(@MappingTarget Student target, Student source);

    //OUPUTS DTO
    StudentOutDtoOnly toDtoOnly(Student studentById);

    @Mappings({
            @Mapping(target = "personOutDtoMini", source="person"),
            @Mapping(target = "subjectOutDtoOnlyList", source = "subjectList"),
            @Mapping(target = "teacherOutDtoOnly", source = "teacherStudent")})
    StudentOutDtoMini toDtoMini(Student student);

    @Mapping(target = "subjectOutDtoOnlyList", source = "subjectList")
    StudentOutDtoSubjects toDtoSubjects (Student student);

    @Mappings({
            @Mapping(target = "subjectOutDtoOnlyList", source = "subjectList"),
            @Mapping(target = "teacherOutDtoOnly", source = "teacherStudent")})
    StudentOutDtoTeacherSubjects toDtoTeacherSubjects(Student student);

    @Mappings({
            @Mapping(target = "personDtoFull", source = "person"),
            @Mapping(target = "teacherOutDtoOnly", source = "teacherStudent"),
            @Mapping(target = "subjectOutDtoMiniList",source = "subjectList")})
    StudentOutDtoFull toDtoFull(Student student);

    List<StudentOutDtoMini> toDtoListMini(List<Student> studentList);

}
