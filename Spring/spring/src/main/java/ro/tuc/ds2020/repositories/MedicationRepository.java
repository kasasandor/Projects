package ro.tuc.ds2020.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.tuc.ds2020.entities.Medication;


public interface MedicationRepository extends JpaRepository<Medication, UUID>{

	Optional<Medication> findByName(String name);
	
	List<Medication> findAllByOrderByNameAsc();
}
