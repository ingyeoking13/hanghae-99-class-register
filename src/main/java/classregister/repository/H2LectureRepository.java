package classregister.repository;

import classregister.domain.Lecture;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public class H2LectureRepository implements LectureRepository{

    @Autowired
    private final EntityManagerFactory emf;
    private final EntityManager em;

    public H2LectureRepository(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = this.emf.createEntityManager();
    }

    @Override
    public Lecture save(String name, LocalDateTime startTime) {
        Lecture result = new Lecture(name, startTime, 30L);
        EntityTransaction tx = this.em.getTransaction();
        tx.begin();
        this.em.persist(result);
        tx.commit();
        return result;
    }

    @Override
    public Optional<Lecture> findById(Long lectureId) {
        return Optional.ofNullable(em.find(Lecture.class, lectureId));
    }

    @Override
    public boolean isLimitedOrDecrease(Long lectureId) {
        EntityManager cur_em = emf.createEntityManager();
        EntityTransaction tx  = cur_em.getTransaction();
        tx.begin();
        Lecture lecture = cur_em.find(Lecture.class, lectureId, LockModeType.PESSIMISTIC_WRITE);
        boolean result = lecture.getQuantity() <= 0;
        if (result) {
            tx.commit();
            return true;
        }
        lecture.setQuantity(lecture.getQuantity() - 1L);
        cur_em.persist(lecture);
        tx.commit();
        return false;
    }

}
