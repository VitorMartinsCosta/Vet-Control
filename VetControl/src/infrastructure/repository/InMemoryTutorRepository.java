package infrastructure.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import domain.model.Tutor;
import domain.repository.TutorRepository;

public class InMemoryTutorRepository implements TutorRepository{

    private final Map<String, Tutor> tutorRepo = new HashMap<>();

    @Override
    public Tutor save(Tutor tutor) {
        tutorRepo.put(tutor.getId(), tutor);
        return tutor;
    }

    @Override
    public List<Tutor> findAll() {
        return tutorRepo.values()
        .stream()
        .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        tutorRepo.remove(id);
    }

    @Override
    public boolean existsById(String id) {
        return tutorRepo.containsKey(id);
    }

    @Override
    public Optional<Tutor> findById(String id) {
        return Optional.ofNullable(tutorRepo.get(id));
    }

    @Override
    public Optional<Tutor> findByCpf(String cpf) {
        return tutorRepo.values()
                        .stream()
                        .filter(t -> t.getCpf().equals(cpf))
                        .findAny();
    }
}
