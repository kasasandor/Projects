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

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.services.PatientService;

@RestController
@CrossOrigin
@RequestMapping(value = "/patient")
public class PatientController {

	private final PatientService patientService;
	
	@Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping()
    public ResponseEntity<List<PatientDTO>> getPatients() {
        List<PatientDTO> dtos = patientService.findPatients();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody PatientDetailsDTO patientDTO) {
        UUID patientID = patientService.insert(patientDTO);
        return new ResponseEntity<>(patientID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") UUID patientID) {
    	PatientDTO dto = patientService.findPatientById(patientID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID id, @Valid @RequestBody PatientDTO patientDTO) {
    	UUID dID = patientService.update(id, patientDTO);
    	return new ResponseEntity<>(dID, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deletePatient(@PathVariable("id") UUID patientID){
    	UUID pID = patientService.delete(patientID);
    	return new ResponseEntity<>(pID, HttpStatus.OK);
    }
}
