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
    private final EntityManager em;

    public H2MemberRepository(EntityManagerFactory emf) {
        this.emf = emf;
        this.em = this.emf.createEntityManager();
    }

    @Override
    public Member save() {
        Member result = new Member();
        EntityTransaction tx = this.em.getTransaction();
        tx.begin();
        this.em.persist(result);
        tx.commit();
        return result;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }
}
