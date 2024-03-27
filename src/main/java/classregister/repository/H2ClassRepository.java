package classregister.repository;


import classregister.domain.Class;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Query;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class H2ClassRepository implements ClassRepository {


    @Autowired
    private final EntityManager em;

    public H2ClassRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Class save(Class classObj) {
        final Query lockQuery = em.createNativeQuery("SELECT * FROM class FOR UPDATE;");
        em.joinTransaction();
        lockQuery.getResultList();
        this.em.persist(classObj);

        return classObj;
    }

    @Override
    public List<Class> findAllByLectureId(Long lectureId) {
        final Query lockQuery = em.createNativeQuery("SELECT * FROM class FOR UPDATE;");
        em.joinTransaction();
        lockQuery.getResultList();
        List<Class> result = this.em.createQuery(
                "select c from Class c where c.classId.lectureId = :lecture_id", Class.class
                ).setParameter("lecture_id", lectureId)
                .setLockMode(LockModeType.PESSIMISTIC_READ)
                .getResultList();
        return result;
    }
}
