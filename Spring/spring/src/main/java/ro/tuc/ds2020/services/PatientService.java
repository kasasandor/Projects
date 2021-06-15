package ro.tuc.ds2020.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caretaker;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.PatientRepository;

@Service
public class PatientService {


	private static final Logger LOGGER = LoggerFactory.getLogger(PatientService.class);
    private final PatientRepository patientRepository;
    
    @Autowired
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    
    public List<PatientDTO> findPatients() {
        List<Patient> patientList = patientRepository.findAllByOrderByNameAsc();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }
    
    public PatientDTO findPatientById(UUID id) {
        Optional<Patient> prosumerOptional = patientRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with id {} was not found in db.", id);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with id: " + id);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }
    
    public PatientDTO findPatientrByName(String name) {
        Optional<Patient> prosumerOptional = patientRepository.findByName(name);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Patient with name {} was not found in db.", name);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with name: " + name);
        }
        return PatientBuilder.toPatientDTO(prosumerOptional.get());
    }

    public UUID insert(PatientDetailsDTO patientDTO) {
    	Patient patient = PatientBuilder.toEntity(patientDTO);
    	patient = patientRepository.save(patient);
        LOGGER.debug("Caretaker with id {} was inserted in db.", patient.getId());
        return patient.getId();
    }
    
    public UUID update(UUID id, PatientDTO patientDTO) {
    	Patient patient = PatientBuilder.toEntity(patientDTO);
    	patient.setId(id);
    	Patient original = patientRepository.findById(patient.getId()).get();
    	patient.setDob(original.getDob());
    	patient.setGender(original.getGender());
    	patient = patientRepository.save(patient);
    	LOGGER.debug("Patient with id {} was updated.", patient.getId());
    	return patient.getId();
    }
    
    public UUID delete(UUID patientID) {
    	patientRepository.deleteById(patientID);
    	return patientID;
    }
}
