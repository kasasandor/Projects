package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class PatientDTO {

	private UUID id;
    private String name;
    private String address;
    private Date dob;
    
    public PatientDTO() {
    	
    }
    
    public PatientDTO(UUID id, String name, String address, Date dob) {
    	this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
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
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDOB() {
        return dob;
    }

    public void setDOB(Date dob) {
        this.dob = dob;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientDTO patientDTO = (PatientDTO) o;
        return  Objects.equals(name, patientDTO.name) &&
                Objects.equals(address, patientDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}