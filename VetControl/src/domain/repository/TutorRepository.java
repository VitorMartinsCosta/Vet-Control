package domain.repository;

import java.util.List;
import java.util.Optional;

import domain.model.Tutor;

public interface TutorRepository {

    Tutor save(Tutor tutor);    
    Optional<Tutor> findById(String id);
    Optional<Tutor> findByCpf(String cpf);
    List<Tutor> findAll();
    void deleteById(String id);
    boolean existsById(String id);

}
