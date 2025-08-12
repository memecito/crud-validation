package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Teacher;
import es.nter.crud_validation.presentation.dto.teacher.TeacherOutDtoOnly;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherOutDtoOnly toDtoOnly(Teacher teacher);
}
