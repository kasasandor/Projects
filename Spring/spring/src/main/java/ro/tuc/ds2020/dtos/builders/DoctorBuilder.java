package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.DoctorDTO;
import ro.tuc.ds2020.dtos.DoctorDetailsDTO;
import ro.tuc.ds2020.entities.Doctor;

public class DoctorBuilder {

	private DoctorBuilder() {
		
	}
	
	public static DoctorDTO toDoctorDTO(Doctor doctor) {
        return new DoctorDTO(doctor.getId(), doctor.getName(), doctor.getAddress(), doctor.getAge());
    }
	
	public static Doctor toEntity(DoctorDetailsDTO doctorDetailsDTO) {
        return new Doctor(doctorDetailsDTO.getName(),
        		doctorDetailsDTO.getAddress(),
        		doctorDetailsDTO.getAge());
    }
}
