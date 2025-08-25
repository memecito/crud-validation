package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Subject;

import es.nter.crud_validation.presentation.dto.subject.SubjectInputDto;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoFull;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoMini;
import es.nter.crud_validation.presentation.dto.subject.SubjectOutDtoOnly;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    SubjectMapper INSTANCE = Mappers.getMapper(SubjectMapper.class);

    //INPUTS DTO
    Subject toModel (SubjectInputDto subjectInputDto);

    Subject update(@MappingTarget Subject target, Subject source);

    //OUPUTS DTO
    SubjectOutDtoOnly toDtoOnly(Subject subject);

    @Mapping(target ="studentListOnly", source = "studentList")
    SubjectOutDtoMini toDtoMini(Subject subject);

    @Mapping(target ="studentOutDtoFullList", source = "studentList")
    SubjectOutDtoFull toDtoFull(Subject subject);

    List<Subject> toMOdelList (List<SubjectInputDto> subjectInputDtoList);

}
