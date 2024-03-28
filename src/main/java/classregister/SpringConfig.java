package classregister;

import classregister.repository.*;
import classregister.service.ClassService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public ClassRepository classRepository(EntityManagerFactory emf) {
//        return new MemoryLectureRepository();
        return new H2ClassRepository(emf);
    }

    @Bean
    public LectureRepository lectureRepository(EntityManagerFactory emf) {
        return new H2LectureRepository(emf);
    }

    @Bean
    public MemberRepository memberRepository(EntityManagerFactory emf) {
        return new H2MemberRepository(emf);
    }

}
