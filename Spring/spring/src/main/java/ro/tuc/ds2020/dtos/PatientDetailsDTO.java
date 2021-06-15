package ro.tuc.ds2020.dtos;

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;

public class PatientDetailsDTO {

	private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String gender;
    @NotNull
    private Date dob;
    
    public PatientDetailsDTO() {
    	
    }
    
    public PatientDetailsDTO(String name, String address, String gender, Date dob) {
    	this.name = name;
        this.gender = gender;
        this.address = address;
        this.dob = dob;
    }
    
    public PatientDetailsDTO(UUID id, String name, String address, String gender, Date dob) {
    	this.id = id;
    	this.name = name;
        this.gender = gender;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
