package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PatientDTO;
import ro.tuc.ds2020.dtos.PatientDetailsDTO;
import ro.tuc.ds2020.entities.Patient;

public class PatientBuilder {

	private PatientBuilder() {
		
	}
	
	public static PatientDTO toPatientDTO(Patient patient) {
        return new PatientDTO(patient.getId(), 
        					patient.getName(), 
        					patient.getAddress(),
							patient.getDob());
    }
	
	public static Patient toEntity(PatientDetailsDTO patientDetailsDTO) {
        return new Patient(patientDetailsDTO.getName(),
        		patientDetailsDTO.getAddress(),
        		patientDetailsDTO.getGender(),
        		patientDetailsDTO.getDob());
    }
	
	public static Patient toEntityWithID(PatientDetailsDTO patientDetailsDTO) {
        return new Patient(patientDetailsDTO.getId(),
        		patientDetailsDTO.getName(),
        		patientDetailsDTO.getAddress(),
        		patientDetailsDTO.getGender(),
        		patientDetailsDTO.getDob());
    }
	
	public static Patient toEntity(PatientDTO patientDTO) {
        return new Patient(patientDTO.getName(),
        		patientDTO.getAddress());
    }
}
