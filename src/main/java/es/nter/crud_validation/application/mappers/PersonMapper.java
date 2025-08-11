package es.nter.crud_validation.application.mappers;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.person.PersonDto;
import es.nter.crud_validation.presentation.dto.person.PersonInputDto;
import es.nter.crud_validation.presentation.dto.person.PersonOutDtoMini;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDtoStandard(Person person);
    PersonOutDtoMini toDtoMini(Person person);

    Person toModelStandard(PersonInputDto personInputDto);

    Person update(@MappingTarget Person target, Person source);

}
