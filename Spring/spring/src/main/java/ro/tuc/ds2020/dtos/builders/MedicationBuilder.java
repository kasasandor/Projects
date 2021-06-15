package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.MedicationDTO;
import ro.tuc.ds2020.dtos.MedicationDetailsDTO;
import ro.tuc.ds2020.entities.Medication;

public class MedicationBuilder {

	private MedicationBuilder() {
		
	}
	
	public static MedicationDTO toMedicationDTO(Medication medication) {
        return new MedicationDTO(medication.getId(), 
        		medication.getName(),
        		medication.getDosage());
    }
	
	public static Medication toEntity(MedicationDetailsDTO medicationDetailsDTO) {
        return new Medication(medicationDetailsDTO.getName(),
        		medicationDetailsDTO.getSideEffects(),
        		medicationDetailsDTO.getDosage());
    }
	
	public static Medication toEntity(MedicationDTO medicationDTO) {
        return new Medication(medicationDTO.getName(),
        		medicationDTO.getDosage());
    }
}
