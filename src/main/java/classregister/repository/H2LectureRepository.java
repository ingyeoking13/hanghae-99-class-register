package classregister.repository;

import classregister.domain.Lecture;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Transactional
public class H2LectureRepository implements LectureRepository{

    @Autowired
    private final EntityManager em;

    public H2LectureRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Lecture save(String name, LocalDateTime startTime) {
        Lecture result = new Lecture(name, startTime);
        this.em.persist(result);
        return result;
    }

    @Override
    public Optional<Lecture> findById(Long lectureId) {
        return Optional.ofNullable(em.find(Lecture.class, lectureId));
    }
}
