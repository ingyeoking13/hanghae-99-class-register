package classregister.repository;

import classregister.domain.Class;
import classregister.domain.ClassId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository {
    Class save(Class classObj);
    List<Class> findAllByLectureId(Long classId);
    Optional<Class> findById(ClassId classId);
}
