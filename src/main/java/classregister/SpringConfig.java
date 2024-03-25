package classregister;

import classregister.repository.*;
import classregister.service.ClassService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ClassRepository classRepository(EntityManager em) {
//        return new MemoryLectureRepository();
        return new H2ClassRepository(em);
    }

    @Bean
    public LectureRepository lectureRepository(EntityManager em) {
        return new H2LectureRepository(em);
    }

    @Bean
    public MemberRepository memberRepository(EntityManager em) {
        return new H2MemberRepository(em);
    }

}
