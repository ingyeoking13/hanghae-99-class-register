package classregister.repository;

import classregister.domain.Lecture;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LectureRepository {
    Lecture save(String name, LocalDateTime startTime);
    Optional<Lecture> findById(Long lectureId);
    boolean isLimitedOrDecrease(Long lectureId);
}
