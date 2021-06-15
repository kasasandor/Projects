package ro.tuc.ds2020.dtos;

import java.util.Objects;
import java.util.UUID;

public class CaretakerDTO {

	private UUID id;
    private String name;
    private String address;
    
    public CaretakerDTO() {
    	
    }
    
    public CaretakerDTO(UUID id, String name, String address) {
    	this.id = id;
        this.name = name;
        this.address = address;
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaretakerDTO caretakerDTO = (CaretakerDTO) o;
        return  Objects.equals(name, caretakerDTO.name) &&
                Objects.equals(address, caretakerDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
