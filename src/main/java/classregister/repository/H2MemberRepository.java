package classregister.repository;

import classregister.domain.Member;
import jakarta.persistence.EntityManager;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public class H2MemberRepository implements MemberRepository {
    private final EntityManager em;

    public H2MemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save() {
        Member result = new Member();
        this.em.persist(result);
        return result;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }
}
