package classregister.repository;


import classregister.domain.Class;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class H2ClassRepository implements ClassRepository {

    private final EntityManager em;

    public H2ClassRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public void save(Class classObj) {
        this.em.persist(classObj);
    }

    @Override
    public Optional<Class> findById(String classId) {
        return Optional.empty();
    }
}
