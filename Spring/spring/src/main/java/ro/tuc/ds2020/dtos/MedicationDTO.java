package ro.tuc.ds2020.dtos;

import java.util.Objects;
import java.util.UUID;

public class MedicationDTO {

	private UUID id;
	private String name;
	private String dosage;
	
	public MedicationDTO() {
		
	}
	
	public MedicationDTO(UUID id, String name, String dosage) {
		this.id = id;
		this.name = name;
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
	
	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicationDTO medicationDTO = (MedicationDTO) o;
        return  Objects.equals(name, medicationDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dosage);
    }
}
