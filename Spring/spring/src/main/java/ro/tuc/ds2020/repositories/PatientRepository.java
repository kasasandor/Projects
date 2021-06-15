package ro.tuc.ds2020.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.tuc.ds2020.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient, UUID>{

	Optional<Patient> findByName(String name);
	
	List<Patient> findAllByOrderByNameAsc();
}
