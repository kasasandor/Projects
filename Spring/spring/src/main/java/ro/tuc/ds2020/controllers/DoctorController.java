package ro.tuc.ds2020.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.services.DoctorService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {

	
	private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping()
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        List<DoctorDTO> dtos = doctorService.findDoctors();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<UUID> insertProsumer(@Valid @RequestBody DoctorDetailsDTO doctorDTO) {
        UUID doctorID = doctorService.insert(doctorDTO);
        return new ResponseEntity<>(doctorID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DoctorDTO> getDoctor(@PathVariable("id") UUID doctorID) {
    	DoctorDTO dto = doctorService.findDoctorById(doctorID);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<UUID> updateProsumer(@PathVariable("id") UUID id, @Valid @RequestBody DoctorDetailsDTO doctorDTO) {
    	UUID dID = doctorService.update(id, doctorDTO);
    	return new ResponseEntity<>(dID, HttpStatus.OK);
    }
    
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UUID> deleteDoctor(@PathVariable("id") UUID doctorID){
    	UUID doctorId = doctorService.delete(doctorID);
    	return new ResponseEntity<>(doctorId, HttpStatus.OK);
    }
}
