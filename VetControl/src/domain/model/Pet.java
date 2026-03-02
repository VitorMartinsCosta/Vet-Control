package domain.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import domain.enums.AnimalSpecies;
import domain.enums.PetStatus;
import domain.exceptions.BusinessRuleException;
import domain.exceptions.ValidationException;

public class Pet {
    private final String id;
    private String name;
    private AnimalSpecies species;
    private String breed;
    private LocalDate birthDate;
    private double weight;
    private Tutor tutor;
    private PetStatus petStatus;

    Pet(String name, AnimalSpecies species, String breed, LocalDate birthDate, double weight) {
        validateAnimalSpecie(species);
        validateBreed(breed);
        validateName(name);
        validateWeight(weight);
        validateBirthDate(birthDate);
        
        this.id = UUID.randomUUID().toString() ;
        this.name = name.trim();
        this.species = species;
        this.breed = breed.trim();
        this.birthDate = birthDate;
        this.weight = weight;
        this.petStatus = PetStatus.ACTIVE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public AnimalSpecies getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public double getWeight() {
        return weight;
    }

    public void updateWeight(double weight) {
        validateStatus();
        validateWeight(weight);
        this.weight = weight;
    }

    public Tutor getTutor() {
        return tutor;
    }

    void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void markAsDeceased(){
        if(petStatus == PetStatus.DECEASED){
            throw new BusinessRuleException("Invalid Operation: Pet is already DECEASED");
        }
        this.petStatus = PetStatus.DECEASED;
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

        Pet other = (Pet) obj;

        return Objects.equals(this.id, other.id);
    }

    private void validateStatus(){
        if(this.petStatus != PetStatus.ACTIVE){
            throw new ValidationException("Invalid pet status: status must be Active.");
        }
    }

    private void validateName(String name){
        if(name == null || name.isBlank()){
            throw new ValidationException("Invalid pet name: name must not be null or blank.");
        }
    }

    private void validateAnimalSpecie(AnimalSpecies animalSpecies){
        if(animalSpecies == null){
            throw new ValidationException("Invalid animal species: species must not be null.");
        }
    }

    private void validateBreed(String breed){
        if(breed == null || breed.isBlank()){
            throw new ValidationException("Invalid breed: breed must not be null or blank.");
        }
    }

    private void validateBirthDate(LocalDate birthDate){
        if(birthDate == null || birthDate.isAfter(LocalDate.now())){
            throw new ValidationException("Invalid birth date: birth date must not be null or in the future.");
        }
    }
    
    private void validateWeight(double weight){
        if(weight <= 0.00){
            throw new ValidationException("Invalid weight: weight must be greater than zero.");
        }
    }

    @Override
    public String toString() {
        return "Pet - " + name + "\n" +
            "Specie: " + species + "\n" +
            "Breed: " + breed + "\n" +
            "BirthDate: " + birthDate + "\n" +
            "Status: " + petStatus + "\n" +
            "Tutor: " + (tutor != null ? tutor.getName() : "none") + "\n";
    }
}
