package classregister.repository;


import classregister.domain.Class;
import classregister.domain.ClassId;
import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class H2ClassRepository implements ClassRepository {


    @Autowired
    private final EntityManagerFactory emf;

    public H2ClassRepository(EntityManagerFactory emf) {
        this.emf= emf;
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

    public Optional<Class> findById(ClassId classId) {
        EntityManager cur_em = this.emf.createEntityManager();
        EntityTransaction tx = cur_em.getTransaction();
        tx.begin();
        Optional<Class> result = Optional.ofNullable(cur_em.find(
                Class.class,
                classId
        ));
        tx.commit();
        return result;

    }

}
