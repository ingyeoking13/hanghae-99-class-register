package classregister.repository;

import classregister.domain.Lecture;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class H2LectureRepository implements LectureRepository{

    private final EntityManager em;

    public H2LectureRepository(EntityManager em) {
        this.em = em;
    }
    @Override
    public Optional<Lecture> findById(Long lectureId) {
        return Optional.ofNullable(em.find(Lecture.class, lectureId));
    }
}
