package ro.tuc.ds2020.controllers;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.tuc.ds2020.dtos.CaretakerDTO;
import ro.tuc.ds2020.dtos.CaretakerDetailsDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.services.CaretakerService;

@RestController
@CrossOrigin
@RequestMapping(value = "/caretaker")
public class CaretakerController {

	private final CaretakerService caretakerService;
	
	@Autowired
    public CaretakerController(CaretakerService caretakerService) {
        this.caretakerService = caretakerService;
    }

    @GetMapping()
    public ResponseEntity<List<CaretakerDTO>> getCaretakers() {
        List<CaretakerDTO> dtos = caretakerService.findCaretakers();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody CaretakerDetailsDTO caretakerDTO) {
        UUID doctorID = caretakerService.insert(caretakerDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CaretakerDTO> getDoctor(@PathVariable("id") UUID caretakerID) {
    	CaretakerDTO dto = caretakerService.findCaretakerById(caretakerID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID id, @Valid @RequestBody CaretakerDTO caretakerDTO) {
    	UUID dID = caretakerService.update(id, caretakerDTO);
    	return new ResponseEntity<>(dID, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteDoctor(@PathVariable("id") UUID caretakerID){
    	UUID cID = caretakerService.delete(caretakerID);
    	return new ResponseEntity<>(cID, HttpStatus.OK);
    }
    
    @PutMapping(value = "/patient/{id}")
    public ResponseEntity<UUID> addPatient(@PathVariable("id") UUID id, @Valid @RequestBody PatientDTO patientDTO){
    	UUID ID = caretakerService.addPatient(id, patientDTO);
    	return new ResponseEntity<>(ID, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}/patients")
    public ResponseEntity<List<PatientDTO>> getPatients(@PathVariable("id") UUID caretakerID){
    	List<PatientDTO> dtos = caretakerService.getPatients(caretakerID);
    	return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
