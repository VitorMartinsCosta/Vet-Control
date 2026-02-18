package domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

import domain.exceptions.ValidationException;

public class Tutor {
    private final String id;
    private String name;
    private String cpf;
    private String phone;
    private String email;

    private Address address;

    private final List <Pet> pets;

    public Tutor(String name, String cpf, String phone, String email, Address address) {
        if(Stream.of(name, cpf, phone, email).anyMatch(v -> v == null || v.isBlank()) || address == null){
            throw new ValidationException("Invalid Tutor: name, CPF, phone, email, and address are required and must not be null or blank.");
        }
        this.id = UUID.randomUUID().toString();
        this.name = name.trim();
        this.cpf = cpf.trim();
        this.phone = phone.trim();
        this.email = email.trim();
        this.address = address;
        this.pets = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Address getAddress() {
        return address;
    }

    public void updateAddress(Address address) {
        validateAddress(address);
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void updatePhone(String phone) {
        validatePhone(phone);
        this.phone = phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void updateEmail(String email) {
        validateEmail(email);
        this.email = email.trim();
    }

    public List <Pet> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public void addPet(Pet pet){
        if(pet == null){
            throw new ValidationException("Cannot add pet: pet must not be null.");
        } else if(pets.contains(pet)){
            throw new ValidationException("Cannot add pet: this pet is already associated with the tutor.");
        } else if(pet.getTutor() != null){
            throw new ValidationException("Cannot add pet: this pet is already associated with other tutor.");
        }
        pets.add(pet);
        pet.setTutor(this);
    }

    public void removePet(Pet pet){ //Devo verificar depois este código, pois um pet não pode ficar orfão.
        if(pet == null){
            throw new ValidationException("Cannot remove pet: pet must not be null.");
        } else if(!pets.contains(pet)){
            throw new ValidationException("Cannot remove pet: this pet is not associated with the tutor.");
        }
        pets.remove(pet);
    }      

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;

        Tutor other = (Tutor) o;

        return Objects.equals(this.id, other.id);
    }  
    
    private void validateAddress(Address address){
        if(address == null){
            throw new ValidationException("Invalid address assignment: address must not be null.");
        }
    }

    private void validatePhone(String phone){
        if (phone == null || phone.isBlank()){
            throw new ValidationException("Update contact information failed: phone must not be null or blank.");
        }
    }

    private void validateEmail(String email){
        if (email == null || email.isBlank()){
            throw new ValidationException("Update contact information failed: email must not be null or blank.");
        }
    }

}
