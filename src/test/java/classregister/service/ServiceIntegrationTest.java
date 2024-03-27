package classregister.service;

import classregister.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class ServiceIntegrationTest {
    @Autowired
    private ClassService classService;
    @Autowired
    private EntityManager em;

    @Autowired
    private H2ClassRepository classRepository;

    @Autowired
    private H2LectureRepository lectureRepository;

    @Autowired
    private H2MemberRepository memberRepository;

//    @BeforeEach
//    @org.springframework.transaction.annotation.Transactional(propagation = Propagation.REQUIRES_NEW)
    @Commit
    public void insertMemberAndLecture(){
        this.lectureRepository.save("A", LocalDateTime.now());
        this.lectureRepository.save("B", LocalDateTime.now());
        this.lectureRepository.save("C", LocalDateTime.now());

        for (int i=0; i<60; i++) {
            this.memberRepository.save();
        }
    }


    @Test
    public void test_30개_성공() {
        for (int i=1; i<=30; i++) {
            this.classService.registerClass((long)i, 1L);
        }
    }

    @Test
    public void test_31개_실패() {
        assertThrows(NullPointerException.class,
        () -> {
            for (int i=1; i<=31; i++) {
                this.classService.registerClass((long)i, 1L);
            }
        });
    }

    @Test
    public void test_동시성환경테스트() throws Exception {

        // given
        int numThreads = 60;

        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch doneSignal = new CountDownLatch(numThreads);
        AtomicInteger s = new AtomicInteger(0);
        AtomicInteger f = new AtomicInteger(0);

         // when
        for (int i=1; i<=numThreads; i++) {
            long finalI = i;
            executorService.execute(() -> {
                try {
                    this.classService.registerClass(finalI, 1L);
                    s.incrementAndGet();
                } catch(Exception e) {
                    System.out.println(e);
                    f.incrementAndGet();
                }
                doneSignal.countDown();
            });
        }
        doneSignal.await();

        Assertions.assertThat(s.get()).isEqualTo(30);
        Assertions.assertThat(f.get()).isEqualTo(30);
    }
}

