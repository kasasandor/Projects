package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;

public class PersonBuilder {

    private PersonBuilder() {
    }

    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getUsername(), person.getRole(), person.getId());
    }

    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getUsername(),
                personDetailsDTO.getPassword(),
                personDetailsDTO.getRole());
    }

    public static PersonDTO toPersonRoleDTO(Person person){
        return new PersonDTO(person.getId(), person.getRole());
    }
}
