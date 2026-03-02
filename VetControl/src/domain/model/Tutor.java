package domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import domain.enums.AnimalSpecies;
import domain.enums.PetStatus;
import domain.enums.TutorStatus;
import domain.exceptions.BusinessRuleException;
import domain.exceptions.ValidationException;

public class Tutor {
    private final String id;
    private String name;
    private String cpf;
    private String phone;
    private String email;
    private TutorStatus tutorStatus;
    private Address address;

    private final List <Pet> pets;

    public Tutor(String name, String cpf, String phone, String email, Address address) {
        validateName(name);
        validateCpf(cpf);
        validatePhone(phone);
        validateEmail(email);
        validateAddress(address);

        this.tutorStatus = TutorStatus.ACTIVE;
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
        validateTutorStatus();
        validateAddress(address);
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void updatePhone(String phone) {
        validateTutorStatus();
        validatePhone(phone);
        this.phone = phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void updateEmail(String email) {
        validateTutorStatus();
        validateEmail(email);
        this.email = email.trim();
    }

    public TutorStatus getTutorStatus(){
        return tutorStatus;
    }

    public void activate(){
        if(tutorStatus == TutorStatus.ACTIVE){
            throw new BusinessRuleException("Invalid Tutor Status: Tutor is already ACTIVE.");
        }
        tutorStatus = TutorStatus.ACTIVE;
    }

    public void deactivate(){
        if(tutorStatus == TutorStatus.INACTIVE){
            throw new BusinessRuleException("Invalid Tutor Status: Tutor is already INACTIVE.");
        }
        tutorStatus = TutorStatus.INACTIVE;
    }

    public List <Pet> getPets() {
        return Collections.unmodifiableList(pets);
    }

    public Pet createPet(String name, AnimalSpecies species, String breed, LocalDate birthDate, double weight){
        validateTutorStatus();
        Pet pet = new Pet(name, species, breed, birthDate, weight);
        addPet(pet);
        return pet;
    }

    public void addPet(Pet pet){
        if(pet == null){
            throw new ValidationException("Cannot add pet: pet must not be null.");
        } 
        
        validateTutorStatus();

        if(pets.contains(pet)){
            throw new ValidationException("Cannot add pet: this pet is already associated with the tutor.");
        }
        if (pet.getPetStatus() != PetStatus.ACTIVE){
            throw new ValidationException("Cannot add pet: pet is not ACTIVE (current status: " + 
            pet.getPetStatus() + ")");
        } 
        pet.setTutor(this);
        pets.add(pet);
    }     

    public void removePet(Pet pet){
        if(pet == null){
            throw new ValidationException("Cannot remove pet: pet must not be null.");
        }else if(!pets.contains(pet)){
            throw new ValidationException("Cannot remove pet: this pet is not associated with the tutor.");
        }
        pets.remove(pet);
        pet.setTutor(null);
    }

    public Optional <Pet> findPetById(String id){
        return pets.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
        return true;
        if (obj == null) 
        return false;
        if (getClass() != obj.getClass()) 
        return false;

        Tutor other = (Tutor) obj;

        return Objects.equals(this.id, other.id);
    }  
    
    //Métodos para validação
    private void validateName(String name){
        if(name == null || name.isBlank()){
            throw new ValidationException("Invalid tutor name: name must not be null or blank.");
        }
    }

    private void validateCpf(String cpf){
        if(cpf == null || cpf.isBlank()){
            throw new ValidationException("Invalid CPF: CPF must not be null or blank.");
        }
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

    private void validateTutorStatus(){
        if(this.tutorStatus != TutorStatus.ACTIVE){
            throw new BusinessRuleException("Invalid Tutor Status: Tutor must be ACTIVE.");
        }
    }
}
