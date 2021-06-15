package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
public class Caretaker implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")
    private UUID id;
	
	@Column(name = "name", nullable = false)
    private String name;
	
	@Column(name = "dob", nullable = false)
    private Date dob;
	
	@Column(name = "gender", nullable = false)
    private String gender;
	
	@Column(name = "address", nullable = false)
    private String address;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "caretaker", cascade = CascadeType.REFRESH)
    private List<Patient> patients;
	
	public Caretaker() {
		
	}
	
	public Caretaker(String name, String address, String gender, Date dob) {
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
	}
	
	public Caretaker(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	
	@Override
	public String toString() {
		return "Caretaker [id=" + id + ", name=" + name + ", address=" + address + "]";
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public List<Patient> getPatients(){
		return this.patients;
	}
	
	public void addPatient(Patient p) {
		if(this.patients == null) {
			this.patients = new ArrayList<>();
		}
		this.patients.add(p);
		p.setCaretaker(this);
	}
	
	public void removePatient(Patient p) {
		this.patients.remove(p);
	}
    
}
