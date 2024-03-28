package classregister.repository;


import classregister.domain.Class;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class H2ClassRepository implements ClassRepository {


    @Autowired
    private final EntityManagerFactory emf;

    private final EntityManager em;

    public H2ClassRepository(EntityManagerFactory emf) {
        this.emf= emf;
        this.em = this.emf.createEntityManager();
    }

    @Override
    public Class save(Class classObj) {
        EntityManager cur_em = this.emf.createEntityManager();
        EntityTransaction tx = cur_em.getTransaction();
        tx.begin();
        cur_em.persist(classObj);
        tx.commit();
        return classObj;
    }

    @Override
    public List<Class> findAllByLectureId(Long lectureId) {
        EntityManager cur_em = this.emf.createEntityManager();
        EntityTransaction tx = cur_em.getTransaction();
        tx.begin();

        List<Class> result = cur_em.createQuery(
                "select c from Class c where c.classId.lectureId = :lecture_id", Class.class
                ).setParameter("lecture_id", lectureId)
                .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                .getResultList();
        tx.commit();
        return result;
    }

}
