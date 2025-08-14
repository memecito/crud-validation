package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.person.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {StudentMapper.class, TeacherMapper.class})
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    //INPUTS
    Person toModelStandard(PersonInputDto personInputDto);

    //OUPUTS
    //@Mapping(target = "studentOutDtoOnly",source = "student")
    PersonDto toDtoStandard(Person person);
    PersonOutDtoMini toDtoMini(Person person);

    @Mapping(target = "studentOutDtoMini",source = "student")
    PersonOutDtoStudent toDtoStudent(Person person);
    @Mapping(target = "teacherOutDtoMini",source = "teacher")
    PersonOutDtoTeacher toDtoTeacher(Person person);
    Person update(@MappingTarget Person target, Person source);

}
