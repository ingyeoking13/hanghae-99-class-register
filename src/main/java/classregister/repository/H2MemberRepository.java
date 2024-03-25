package classregister.repository;

import classregister.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class H2MemberRepository implements MemberRepository {
    private final EntityManager em;

    public H2MemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return Optional.ofNullable(em.find(Member.class, memberId));
    }
}
