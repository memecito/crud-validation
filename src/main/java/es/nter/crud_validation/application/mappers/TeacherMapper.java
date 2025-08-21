package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.presentation.dto.teacher.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StudentMapper.class})
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    // INPUTS DTO
    @Mapping(target = "person", source = "personInputDtoId")
    Teacher toModel(TeacherInputDto teacherInputDto);

    Teacher update(@MappingTarget Teacher target, Teacher source);

    //OUPUTS DTO
    TeacherOutDtoOnly toDtoOnly(Teacher teacher);

    @Mappings({
            @Mapping(target = "personOutDtoMini", source="person"),
            @Mapping(target = "studentOutDtoSubjectsList", source = "studentList")})
    TeacherOutDtoMini toDtoMini(Teacher teacher);

    @Mappings({
            @Mapping(target="personDto", source="person"),
            @Mapping(target = "studentOutDtoMiniList", source = "studentList")})
    TeacherOutDtoPerson toDtoPerson(Teacher teacher);

    @Mappings({
            @Mapping(target = "studentOutDtoOnlyList", source = "studentList")})
    TeacherOutDtoStudents toDtoStudents(Teacher teacher);

    @Mappings({
            @Mapping(target = "personDtoFull", source = "person"),
            @Mapping(target = "studentOutDtoOnlyList", source = "studentList")})
    TeacherOutDtoFull toDtoFull(Teacher teacher);

    List<TeacherOutDtoMini> listToDtoMini(List<Teacher> teacherList);
}
