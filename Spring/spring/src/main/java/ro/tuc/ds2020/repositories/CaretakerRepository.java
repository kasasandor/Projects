package ro.tuc.ds2020.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.tuc.ds2020.entities.Caretaker;

public interface CaretakerRepository extends JpaRepository<Caretaker, UUID>{

	Optional<Caretaker> findByName(String name);
	
	List<Caretaker> findAllByOrderByNameAsc();
}
