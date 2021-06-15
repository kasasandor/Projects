package ro.tuc.ds2020.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import ro.tuc.ds2020.entities.Activity;

public interface ActivityRepository extends JpaRepository<Activity, UUID>{

}
