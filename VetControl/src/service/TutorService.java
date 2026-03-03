package service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import domain.enums.AnimalSpecies;
import domain.exceptions.BusinessRuleException;
import domain.exceptions.EntityNotFoundException;
import domain.exceptions.ValidationException;
import domain.model.Address;
import domain.model.Pet;
import domain.model.Tutor;
import domain.repository.TutorRepository;

public class TutorService {

    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    
    public Tutor saveTutor(String name, String cpf, String phone, String email, Address address){

        tutorRepository.findByCpf(cpf).ifPresent(t -> {
            throw new ValidationException("Registration failed: a tutor with this CPF already exists.");
        });

        Tutor tutor = new Tutor(name, cpf, phone, email, address);
        tutorRepository.save(tutor);
        return tutor;
    }    
    
    public List<Tutor> getAllTutors() {
        return tutorRepository.findAll();
    }

    public Tutor getTutorById(String id){

        if (id == null || id.isBlank()){
                throw new ValidationException("Invalid ID: ID must not be null or blank.");
        }

        return tutorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Tutor not found: no record exists for the provided ID."));
    }

    public Tutor getTutorByCpf(String cpf){
        validateCPF(cpf);
        return tutorRepository.findByCpf(cpf).orElseThrow(() -> new EntityNotFoundException("Tutor not found: no record exists for the provided CPF."));
    }

    public void updateTutorPhone(String tutorCpf, String phone){
        Tutor tutor = getTutorByCpf(tutorCpf);
        tutor.updatePhone(phone);
        tutorRepository.save(tutor);
    }
    
    public void updateTutorEmail(String tutorCpf, String email){
        Tutor tutor = getTutorByCpf(tutorCpf);
        tutor.updateEmail(email);
        tutorRepository.save(tutor);
    }
    
    public void updateTutorAddress(String tutorCpf, Address address){
        Tutor tutor = getTutorByCpf(tutorCpf);
        tutor.updateAddress(address);
        tutorRepository.save(tutor);
    }

    public Tutor activateTutor(String cpf){

        validateCPF(cpf);

        Tutor tutor = getTutorByCpf(cpf);
        tutor.activate();
        tutorRepository.save(tutor);
        return tutor;
    }

    public Tutor deactivateTutor(String cpf){

        validateCPF(cpf);

        Tutor tutor = getTutorByCpf(cpf);
        tutor.deactivate();
        tutorRepository.save(tutor);
        return tutor;
    }

    public List<Pet> getPetsByTutorCpf(String cpf){
        Tutor tutor = getTutorByCpf(cpf);
        return tutor.getPets();
    }

    public Pet getPetById(String petId) {
    for (Tutor tutor : tutorRepository.findAll()) {
        Optional<Pet> pet = tutor.findPetById(petId);
        if (pet.isPresent()) {
            return pet.get();
        }
    }
    throw new EntityNotFoundException("Pet not found: no record exists for the provided ID.");
    }

    public Pet registerPet(String name, AnimalSpecies species, String breed, LocalDate birthDate, double weight, String cpf){

        validateCPF(cpf);

        Tutor tutor = getTutorByCpf(cpf);

        Pet pet = tutor.createPet(name, species, breed, birthDate, weight);

        tutorRepository.save(tutor);
        return pet; 
    }

    //Método depende de garantia transacional.
    public void transferPet(String cpf1, String cpf2, String idPet){

        if (idPet == null || idPet.isBlank()){
            throw new ValidationException("Invalid Pet ID: Pet ID must not be null or blank.");
        }

        Tutor tutor1 = getTutorByCpf(cpf1);
        Tutor tutor2 = getTutorByCpf(cpf2);

        if (tutor1.equals(tutor2)){
            throw new BusinessRuleException("Invalid Tutor CPFs: Source and destination tutor CPFs must be different for pet transfer.");
        }
        Pet pet = tutor1.findPetById(idPet).orElseThrow(()-> new EntityNotFoundException("Pet not found: no record exists for the provided ID."));

        tutor1.removePet(pet);
        tutor2.addPet(pet);

        tutorRepository.save(tutor1);
        tutorRepository.save(tutor2);
    }

    public void markPetAsDeceased(String tutorCpf, String petId){
        
        Tutor tutor = getTutorByCpf(tutorCpf);
        Pet pet = tutor.findPetById(petId)
                .orElseThrow(() -> new EntityNotFoundException("Pet not found: no record exists for the provided ID."));
        pet.markAsDeceased();
        tutorRepository.save(tutor);
    }

    //Métodos auxiliares

    private void validateCPF(String cpf){
        if (cpf == null || cpf.isBlank()){
                throw new ValidationException("Invalid CPF: CPF must not be null or blank.");
        }
    }
}
