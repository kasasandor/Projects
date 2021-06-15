package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Medication implements Serializable{

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
	
	@Column(name = "sideEffects", nullable = false)
    private String sideEffects;
	
	@Column(name = "dosage", nullable = false)
    private String dosage;
	
	public Medication() {
		
	}
	
	public Medication(String name, String sideEffects, String dosage) {
		this.name = name;
		this.sideEffects = sideEffects;
		this.dosage = dosage;
	}
	
	public Medication(String name, String dosage) {
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

	@Override
	public String toString(){
		return name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
