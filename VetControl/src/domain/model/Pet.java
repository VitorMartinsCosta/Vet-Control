package domain.model;

import java.time.LocalDate;

import domain.enums.AnimalSpecies;

public class Pet {
    private Integer id;
    private String name;
    private AnimalSpecies specie;
    private String breed;
    private LocalDate birthDate;
    private Double weight;
    private Tutor tutor;

    public Pet() {
    }

    public Pet(Integer id, String name, AnimalSpecies specie, String breed, LocalDate birthDate, Double weight, Tutor tutor) {
        this.id = id;
        this.name = name;
        this.specie = specie;
        this.breed = breed;
        this.birthDate = birthDate;
        this.weight = weight;
        this.tutor = tutor;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AnimalSpecies getSpecie() {
        return specie;
    }

    public void setSpecie(AnimalSpecies specie) {
        this.specie = specie;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        this.tutor = tutor;
    }
    
}
