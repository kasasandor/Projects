package ro.tuc.ds2020.dtos;

import java.util.Objects;
import java.util.UUID;

public class PersonDTO {
    private UUID id;
    private String username;
    private String password;
    private String role;

    public PersonDTO() {
    }

    public PersonDTO(UUID id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public PersonDTO(String username, String role, UUID id){
        this.id = id;
        this.username = username;
        this.role = role;
    }

    public PersonDTO(UUID id, String role) {
        this.id = id;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return role == personDTO.role &&
                Objects.equals(username, personDTO.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, role);
    }
}
