package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Subject;
import es.nter.crud_validation.presentation.dto.student.StudentInputDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoMini;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    SubjectOutDtoFull toDtoFull(Subject subject);

    SubjectOutDtoMini toDtoMini(Subject subject);

    Subject toModel (StudentInputDto studentInputDto);

    Subject update(@MappingTarget Subject target, Subject source);
}
