package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
public class Patient implements Serializable{

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
	
	@ManyToOne
	@JoinColumn(name = "caretaker_id", nullable = true)
	private Caretaker caretaker;
	
	@Column(name = "medicalRecord", nullable = true)
    private String medicalRecord;
	
	public Patient() {
		
	}
	
	public Patient(String name, String address, String gender, Date dob) {
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
	}
	
	public Patient(UUID id, String name, String address, String gender, Date dob) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.gender = gender;
		this.dob = dob;
	}
	
	public Patient(String name, String address) {
		this.name = name;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", address=" + address + "]";
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

	public Caretaker getCaretaker() {
		return caretaker;
	}

	public void setCaretaker(Caretaker caretaker) {
		this.caretaker = caretaker;
	}
}
