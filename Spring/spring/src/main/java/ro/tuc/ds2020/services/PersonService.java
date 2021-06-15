package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDTO findPersonById(UUID id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDTO(prosumerOptional.get());
    }
    
    public PersonDTO findPersonByUsername(String username) {
        Optional<Person> prosumerOptional = personRepository.findByUsername(username);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with name {} was not found in db", username);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with name: " + username);
        }
        return PersonBuilder.toPersonDTO(prosumerOptional.get());
    }

    public UUID insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }

    public void delete(UUID id) {
    	personRepository.deleteById(id);
    }

    public PersonDTO checkPassword(PersonDTO personDTO){
        Optional<Person> check = personRepository.findByUsername(personDTO.getUsername());
        if (!check.isPresent()) {
            LOGGER.error("Person with name {} was not found in db", personDTO.getUsername());
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with name: " + personDTO.getUsername());
        }
        Person person = check.get();
        if(person.getPassword().equals(personDTO.getPassword())){
            return PersonBuilder.toPersonRoleDTO(person);
        }
        else{
            throw new ResourceNotFoundException("Incorrect password!");
        }
    }
}
