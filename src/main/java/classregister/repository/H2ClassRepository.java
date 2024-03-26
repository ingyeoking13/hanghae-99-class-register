package classregister.repository;


import classregister.domain.Class;
import classregister.domain.ClassId;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class H2ClassRepository implements ClassRepository {

    private final EntityManager em;

    public H2ClassRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Class save(Class classObj) {
        this.em.persist(classObj);
        return classObj;
    }

    @Override
    public List<Class> findAllByLectureId(Long lectureId) {
        List<Class> result = this.em.createQuery(
                "select c from Class c where c.classId.lectureId = :lecture_id", Class.class
                ).setParameter("lecture_id", lectureId)
                .getResultList();
        return result;
    }
}
