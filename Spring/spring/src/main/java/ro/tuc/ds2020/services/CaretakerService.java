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
import ro.tuc.ds2020.dtos.CaretakerDTO;
import ro.tuc.ds2020.dtos.CaretakerDetailsDTO;
import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.dtos.builders.CaretakerBuilder;
import ro.tuc.ds2020.dtos.builders.PatientBuilder;
import ro.tuc.ds2020.entities.Caretaker;
import ro.tuc.ds2020.entities.Patient;
import ro.tuc.ds2020.repositories.CaretakerRepository;
import ro.tuc.ds2020.repositories.PatientRepository;

@Service
public class CaretakerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CaretakerService.class);
    private final CaretakerRepository caretakerRepository;
    private final PatientRepository patientRepository;
    
    @Autowired
    public CaretakerService(CaretakerRepository caretakerRepository, PatientRepository patientRepository) {
        this.caretakerRepository = caretakerRepository;
        this.patientRepository = patientRepository;
    }
    
    public List<CaretakerDTO> findCaretakers() {
        List<Caretaker> caretakerList = caretakerRepository.findAllByOrderByNameAsc();
        return caretakerList.stream()
                .map(CaretakerBuilder::toCaretakerDTO)
                .collect(Collectors.toList());
    }
    
    public CaretakerDTO findCaretakerById(UUID id) {
        Optional<Caretaker> prosumerOptional = caretakerRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caretaker with id {} was not found in db.", id);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with id: " + id);
        }
        return CaretakerBuilder.toCaretakerDTO(prosumerOptional.get());
    }
    
    public CaretakerDTO findCaretakerByName(String name) {
        Optional<Caretaker> prosumerOptional = caretakerRepository.findByName(name);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Caretaker with name {} was not found in db.", name);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with name: " + name);
        }
        return CaretakerBuilder.toCaretakerDTO(prosumerOptional.get());
    }

    public UUID insert(CaretakerDetailsDTO caretakerDTO) {
    	Caretaker caretaker = CaretakerBuilder.toEntity(caretakerDTO);
    	caretaker = caretakerRepository.save(caretaker);
        LOGGER.debug("Caretaker with id {} was inserted in db.", caretaker.getId());
        return caretaker.getId();
    }
    
    public UUID update(UUID id, CaretakerDTO caretakerDTO) {
    	Caretaker caretaker = CaretakerBuilder.toEntity(caretakerDTO);
    	caretaker.setId(id);
    	Caretaker original = caretakerRepository.findById(caretaker.getId()).get();
    	caretaker.setDob(original.getDob());
    	caretaker.setGender(original.getGender());
    	caretaker = caretakerRepository.save(caretaker);
    	LOGGER.debug("Caretaker with id {} was updated.", caretaker.getId());
    	return caretaker.getId();
    }
    
    public UUID delete(UUID dcaretakerID) {
    	caretakerRepository.deleteById(dcaretakerID);
    	return dcaretakerID;
    }
    
    public UUID addPatient(UUID id, PatientDTO patientDTO) {
    	Caretaker caretaker = caretakerRepository.findById(id).get();
    	Patient p = patientRepository.findById(patientDTO.getId()).get();
    			//PatientBuilder.toEntityWithID(patientDTO);
//    	for(Patient pt : caretaker.getPatients()) {
//    		LOGGER.debug(pt.toString());
//    	}
//    	p.setCaretaker(caretaker);
    	caretaker.addPatient(p);
    	p = patientRepository.save(p);
    	//LOGGER.debug(p.getCaretaker().toString());
    	caretaker = caretakerRepository.save(caretaker);
    	
    	Caretaker aux1 = caretakerRepository.findById(id).get();
    	Patient aux2 = patientRepository.findById(patientDTO.getId()).get();
    	
    	for(Patient pt : aux1.getPatients()) {
    		LOGGER.debug("\n" + pt.toString() + "\n");
    	}
    	
    	LOGGER.debug("\n" + aux2.getCaretaker().toString() + "\n");
    	LOGGER.debug("Caretaker with id {} was updated.", caretaker.getId());
    	return caretaker.getId();
    }
    
    public List<PatientDTO> getPatients(UUID ID) {
    	Optional<Caretaker> prosumer = caretakerRepository.findById(ID);
    	if (!prosumer.isPresent()) {
            LOGGER.error("Caretaker with id {} was not found in db.", ID);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with id: " + ID);
        }
    	Caretaker caretaker = prosumer.get();
    	
        List<Patient> patientList = caretaker.getPatients();
        return patientList.stream()
                .map(PatientBuilder::toPatientDTO)
                .collect(Collectors.toList());
    }
}
