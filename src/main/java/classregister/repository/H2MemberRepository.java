package classregister.repository;

import classregister.domain.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class H2MemberRepository implements MemberRepository {

    @Autowired
    private final EntityManagerFactory emf;

    public H2MemberRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public Member save() {
        Member result = new Member();
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(result);
        tx.commit();
        return result;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        EntityManager em = emf.createEntityManager();
        return Optional.ofNullable(em.find(Member.class, memberId));
    }
}
