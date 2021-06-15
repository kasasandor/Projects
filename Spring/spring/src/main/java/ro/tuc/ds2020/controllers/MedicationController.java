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

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationDetailsDTO;
import ro.tuc.ds2020.services.MedicationService;

@RestController
@CrossOrigin
@RequestMapping(value = "/medication")
public class MedicationController {

	private final MedicationService medicationService;
	
	@Autowired
    public MedicationController(MedicationService medicationService) {
        this.medicationService = medicationService;
    }

    @GetMapping()
    public ResponseEntity<List<MedicationDTO>> getMedications() {
        List<MedicationDTO> dtos = medicationService.findMedications();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody MedicationDetailsDTO medicationDTO) {
        UUID medicationID = medicationService.insert(medicationDTO);
        return new ResponseEntity<>(medicationID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable("id") UUID medicationID) {
    	MedicationDTO dto = medicationService.findMedicationById(medicationID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID id, @Valid @RequestBody MedicationDTO medicationDTO) {
    	UUID dID = medicationService.update(id, medicationDTO);
    	return new ResponseEntity<>(dID, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteMedication(@PathVariable("id") UUID medicationID){
    	UUID pID = medicationService.delete(medicationID);
    	return new ResponseEntity<>(pID, HttpStatus.OK);
    }
}
