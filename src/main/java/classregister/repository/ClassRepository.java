package classregister.repository;

import classregister.domain.Class;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository {
    void save(Class classObj);
    Optional<Class> findById(String classId);
}
