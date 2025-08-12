package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Student;
import es.nter.crud_validation.presentation.dto.student.StudentInputDto;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoFull;
import es.nter.crud_validation.presentation.dto.student.StudentOutDtoMini;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface StudentMapper {

    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);

    StudentOutDtoFull toDtoFull(Student student);

    @Mapping(target = "personOutDtoMini", source = "person")
    StudentOutDtoMini toDtoMini(Student student);

    Student toModel(StudentInputDto studentInputDto);
}
