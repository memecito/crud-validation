package es.nter.crud_validation.presentation.mapper;

import es.nter.crud_validation.domain.models.Person;
import es.nter.crud_validation.presentation.dto.PersonDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    PersonDto toDtoStandard(Person person);

    Person toModelStandard(PersonDto personDto);
}
