package classregister.service;

import classregister.repository.LectureRepository;
import classregister.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
@Transactional
public class ServiceIntegrationTest {
    @Autowired
    private ClassService classService;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void insertMemberAndLecture(){
        lectureRepository.save("A", LocalDateTime.now());
        lectureRepository.save("B", LocalDateTime.now());
        lectureRepository.save("C", LocalDateTime.now());

        for (int i=0; i<31; i++) {
            memberRepository.save();
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
        int numThreads = 30;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
        CountDownLatch doneSignal = new CountDownLatch(numThreads);

        // when
        for (int i=1; i<=numThreads; i++) {
            Long longI = (long)i;
            executorService.execute(() -> {
                classService.registerClass(longI, 1L);
                doneSignal.countDown();
            });
        }

        doneSignal.await();

    }



}

