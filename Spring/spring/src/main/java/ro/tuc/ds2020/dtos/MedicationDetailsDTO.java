package ro.tuc.ds2020.dtos;

import java.util.UUID;

import javax.validation.constraints.NotNull;

public class MedicationDetailsDTO {

	private UUID id;
	@NotNull
	private String name;
	@NotNull
	private String sideEffects;
	@NotNull
	private String dosage;
	
	public MedicationDetailsDTO() {
		
	}
	
	public MedicationDetailsDTO(UUID id, String name, String sideEffects, String dosage) {
		this.id = id;
		this.name = name;
		this.sideEffects = sideEffects;
		this.dosage = dosage;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSideEffects() {
		return sideEffects;
	}

	public void setSideEffects(String sideEffects) {
		this.sideEffects = sideEffects;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}
	
}
