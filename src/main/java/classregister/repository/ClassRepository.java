package classregister.repository;

import classregister.domain.Class;
import classregister.domain.ClassId;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository {
    Class save(Class classObj);
    Optional<Class> findById(String classId);
}
