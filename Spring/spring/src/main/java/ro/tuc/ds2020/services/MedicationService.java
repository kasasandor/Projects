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
import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationDetailsDTO;
import ro.tuc.ds2020.dtos.builders.MedicationBuilder;
import ro.tuc.ds2020.entities.Caretaker;
import ro.tuc.ds2020.entities.Medication;
import ro.tuc.ds2020.repositories.MedicationRepository;

@Service
public class MedicationService {

	private static final Logger LOGGER = LoggerFactory.getLogger(MedicationService.class);
    private final MedicationRepository medicationRepository;
    
    @Autowired
    public MedicationService(MedicationRepository medicationRepository) {
        this.medicationRepository = medicationRepository;
    }
    
    public List<MedicationDTO> findMedications() {
        List<Medication> medicationList = medicationRepository.findAllByOrderByNameAsc();
        return medicationList.stream()
                .map(MedicationBuilder::toMedicationDTO)
                .collect(Collectors.toList());
    }
    
    public MedicationDTO findMedicationById(UUID id) {
        Optional<Medication> prosumerOptional = medicationRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with id {} was not found in db.", id);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with id: " + id);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }
    
    public MedicationDTO findMedicationrByName(String name) {
        Optional<Medication> prosumerOptional = medicationRepository.findByName(name);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Medication with name {} was not found in db.", name);
            throw new ResourceNotFoundException(Caretaker.class.getSimpleName() + " with name: " + name);
        }
        return MedicationBuilder.toMedicationDTO(prosumerOptional.get());
    }

    public UUID insert(MedicationDetailsDTO medicationDTO) {
    	Medication medication = MedicationBuilder.toEntity(medicationDTO);
    	medication = medicationRepository.save(medication);
        LOGGER.debug("Caretaker with id {} was inserted in db.", medication.getId());
        return medication.getId();
    }
    
    public UUID update(UUID id, MedicationDTO medicationDTO) {
    	Medication medication = MedicationBuilder.toEntity(medicationDTO);
    	medication.setId(id);
    	Medication original = medicationRepository.findById(medication.getId()).get();
    	medication.setSideEffects(original.getSideEffects());
    	medication = medicationRepository.save(medication);
    	LOGGER.debug("Medication with id {} was updated.", medication.getId());
    	return medication.getId();
    }
    
    public UUID delete(UUID medicationID) {
    	medicationRepository.deleteById(medicationID);
    	return medicationID;
    }
}
