package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.DoctorBuilder;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Doctor;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.DoctorRepository;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DoctorService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DoctorService.class);
    private final DoctorRepository doctorRepository;
    
    @Autowired
    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }
    
    public List<DoctorDTO> findDoctors() {
        List<Doctor> doctorList = doctorRepository.findAllByOrderByNameAsc();
        return doctorList.stream()
                .map(DoctorBuilder::toDoctorDTO)
                .collect(Collectors.toList());
    }
    
    public DoctorDTO findDoctorById(UUID id) {
        Optional<Doctor> prosumerOptional = doctorRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with id {} was not found in db.", id);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + id);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }
    
    public DoctorDTO findDoctorByName(String name) {
        Optional<Doctor> prosumerOptional = doctorRepository.findByName(name);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Doctor with name {} was not found in db.", name);
            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with name: " + name);
        }
        return DoctorBuilder.toDoctorDTO(prosumerOptional.get());
    }

    public UUID insert(DoctorDetailsDTO doctorDTO) {
    	Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
    	doctor = doctorRepository.save(doctor);
        LOGGER.debug("Doctor with id {} was inserted in db.", doctor.getId());
        return doctor.getId();
    }
    
    public UUID update(UUID id, DoctorDetailsDTO doctorDTO) {
    	Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
    	//System.out.println(doctor.toString());
    	doctor.setId(id);
    	Doctor original = doctorRepository.findById(doctor.getId()).get();
    	if(doctor.equals(original)) {
    		LOGGER.debug("Updated is the same as original.");
    	}
    	else {
    		//doctorRepository.delete(original);
    		//original.setName(doctor.getName());
    		//original.setAddress(doctor.getAddress());
    		//original.setAge(doctor.getAge());
    		doctor = doctorRepository.save(doctor);
    		
    	}
    	LOGGER.debug("Doctor with id {} was updated.", doctor.getId());
    	return doctor.getId();
    }
    
    public UUID delete(UUID doctorID) {
//    	Doctor doctor = DoctorBuilder.toEntity(doctorDTO);
//    	doctorRepository.delete(doctor);
//    	LOGGER.debug("Doctor with id {} was deleted.", doctor.getId());
//    	return doctor.getId();
//    	Optional<Doctor> prosumerOptional = doctorRepository.findById(doctorID);
//    	
//    	if(!prosumerOptional.isPresent()) {
//    		LOGGER.error("Doctor with id {} was not found in db.", doctorID);
//            throw new ResourceNotFoundException(Doctor.class.getSimpleName() + " with id: " + doctorID);
//    	}
    	doctorRepository.deleteById(doctorID);
    	return doctorID;
    	
    	
    }
}
