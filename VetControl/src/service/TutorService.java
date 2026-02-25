package service;

import domain.exceptions.ValidationException;
import domain.model.Address;
import domain.model.Tutor;
import domain.repository.TutorRepository;

public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    
    public Tutor createTutor(String name, String cpf, String phone, String email, Address address){

        tutorRepository.findByCpf(cpf).ifPresent(t -> {
            throw new ValidationException("Registration failed: a tutor with this CPF already exists.");
        });

        Tutor tutor = new Tutor(name, cpf, phone, email, address);
        tutorRepository.save(tutor);
        return tutor;
    }    
    
    public Tutor findById(String id){
        if (id == null){
            throw new ValidationException("Invalid ID: ID must not be null or blank.");
        }
        return tutorRepository.findById(id).orElseThrow(()-> new ValidationException("Tutor not found: no record exists for the provided ID."));
    }

    
}
