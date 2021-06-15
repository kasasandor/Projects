package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/login")
public class LoginController {

    private final PersonService personService;

    @Autowired
    public LoginController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping()
    public ResponseEntity<PersonDTO> checkValid(@Valid @RequestBody PersonDTO personDTO) {
        PersonDTO dto = personService.checkPassword(personDTO);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
