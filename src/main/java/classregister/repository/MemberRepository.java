package classregister.repository;

import classregister.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save();
    Optional<Member> findById(Long memberId);
}

