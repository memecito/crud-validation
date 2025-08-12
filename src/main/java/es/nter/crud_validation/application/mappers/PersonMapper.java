package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.person.PersonDto;
import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {StudentMapper.class, TeacherMapper.class})
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);


    @Mapping(target = "studentOutDtoOnly",source = "student")
    PersonDto toDtoStandard(Person person);
    PersonOutDtoMini toDtoMini(Person person);

    Person toModelStandard(PersonInputDto personInputDto);

    Person update(@MappingTarget Person target, Person source);

}
