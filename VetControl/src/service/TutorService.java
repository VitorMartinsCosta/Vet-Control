package service;

import domain.exceptions.ValidationException;
import domain.model.Pet;
import domain.model.Tutor;

public class TutorService {
        public void transferPet(Pet pet, Tutor currentTutor, Tutor newTutor){
        if(pet == null){
            throw new ValidationException("Cannot transfer pet: pet must not be null.");
        } else if(!currentTutor.getPets().contains(pet)){
            throw new ValidationException("Cannot transfer pet: this pet is not associated with the current tutor.");
        }else if(newTutor.getPets().contains(pet)){
            throw new ValidationException("Cannot transfer pet: this pet is already associated with the new tutor.");
        }
        newTutor.addPet(pet);
        currentTutor.getPets().remove(pet);
    }
}
