package ro.tuc.ds2020.dtos;

import java.util.Objects;
import java.util.UUID;

public class DoctorDTO {

	private UUID id;
    private String name;
    private String address;
    private int age;

    public DoctorDTO() {
    	
    }
    
    public DoctorDTO(UUID id, String name, String address, int age) {
    	this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return age == doctorDTO.age &&
                Objects.equals(name, doctorDTO.name) &&
                Objects.equals(address, doctorDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address, age);
    }
}
