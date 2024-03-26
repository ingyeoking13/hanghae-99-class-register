package classregister.repository;


import classregister.domain.Class;
import classregister.domain.ClassId;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class H2ClassRepository implements ClassRepository {

    private final EntityManager em;

    public H2ClassRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Class save(Class classObj) {
        this.em.persist(classObj);
        return classObj;
    }

    @Override
    public Optional<Class> findById(String classId) {
        return Optional.empty();
    }
}
