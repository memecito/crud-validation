package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.presentation.dto.teacher.TeacherInputDto;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoFull;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoMini;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoOnly;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherOutDtoOnly toDtoOnly(Teacher teacher);
    Teacher toModel(TeacherInputDto teacherInputDto);

    Teacher update(@MappingTarget Teacher target, Teacher source);

    @Mappings({
            @Mapping(target = "personMini", source = "person"),
            @Mapping(target = "studentOutDtoMiniList", source = "studentList")
    })
    TeacherOutDtoMini toDtoMini(Teacher teacher);

    @Mappings({
            @Mapping(target = "personMini", source = "person"),
            @Mapping(target = "studentOutDtoMiniList", source = "studentList")
    })
    TeacherOutDtoFull toDtoFull(Teacher teacher);
    List<TeacherOutDtoMini> listToDtoMini(List<Teacher> teacherList);
}
