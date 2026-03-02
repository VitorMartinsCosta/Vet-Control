package service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import domain.enums.AnimalSpecies;
import domain.enums.TutorStatus;
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

        validateId(id, "Tutor");

        return tutorRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Tutor not found: no record exists for the provided ID."));
    }

    public Tutor activateTutor(String id){

        validateId(id, "Tutor");

        Tutor tutor = getTutorById(id);
        tutor.activate();
        tutorRepository.save(tutor);
        return tutor;
    }

    public Tutor deactivateTutor(String id){

        validateId(id, "Tutor");

        Tutor tutor = getTutorById(id);
        tutor.deactivate();
        tutorRepository.save(tutor);
        return tutor;
    }

    public Pet getPetById(String petId) {
    for (Tutor tutor : tutorRepository.findAll()) {
        Optional<Pet> pet = tutor.findPetById(petId);
        if (pet.isPresent()) {
            return pet.get();
        }
    }
    throw new EntityNotFoundException("Pet not found.");
    }

    public Pet registerPet(String name, AnimalSpecies species, String breed, LocalDate birthDate, double weight, String idTutor){

        validateId(idTutor, "Tutor");

        Tutor tutor = getTutorById(idTutor);

        if(tutor.getTutorStatus() != TutorStatus.ACTIVE){
            throw new BusinessRuleException("Invalid Tutor Status: Tutor must be ACTIVE.");
        }

        Pet pet = tutor.createPet(name, species, breed, birthDate, weight);

        tutorRepository.save(tutor);
        return pet; 
    }

    //Método depende de garantia transacional.
    public void transferPet(String idTutor1, String idTutor2, String idPet){

        validateId(idPet, "Pet");

        Tutor tutor1 = getTutorById(idTutor1);
        Tutor tutor2 = getTutorById(idTutor2);

        if (tutor1.equals(tutor2)){
            throw new BusinessRuleException("Invalid Tutor IDs: Source and destination tutor IDs must be different for pet transfer.");
        }
        Pet pet = tutor1.findPetById(idPet).orElseThrow(()-> new EntityNotFoundException("Pet not found: no record exists for the provided ID."));

        tutor1.removePet(pet);
        tutor2.addPet(pet);

        tutorRepository.save(tutor1);
        tutorRepository.save(tutor2);
    }

    //Métodos para validação.
    private void validateId(String id, String type){
        if (id == null || id.isBlank()){
            throw new ValidationException(
                "Invalid " + type + " ID: " + type + " ID must not be null or blank."
            );
        }
    }
}
