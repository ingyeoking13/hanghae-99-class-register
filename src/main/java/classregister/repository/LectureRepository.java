package classregister.repository;

import classregister.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LectureRepository {
    Optional<Lecture> findById(Long lectureId);
}
