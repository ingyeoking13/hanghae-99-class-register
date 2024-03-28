package classregister.service;

import classregister.repository.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@Transactional
public class IntegrationTest {
    @Autowired
    private ClassService classService;

    @BeforeEach
    public void insertMemberAndLecture(){
        this.classService.registerLecture("A");
        this.classService.registerLecture("B");
        this.classService.registerLecture("C");
        for (int i =0; i<60; i++) {
            this.classService.registerMember();
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
        for (int i=1; i<=30; i++) {
            this.classService.registerClass((long)i, 1L);
        }
        assertThrows(NullPointerException.class,
        () -> {
            this.classService.registerClass(31L, 1L);
        });
    }

    @Test
    public void test_동시성환경테스트() {
        // given
        int numThreads = 60;

        ExecutorService executorService = Executors.newFixedThreadPool(100);
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        AtomicInteger successCount = new AtomicInteger(0);
        AtomicInteger failCount = new AtomicInteger(0);

        for (int i = 1; i <= numThreads; i++) {
            long userId = i;
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                try {
                    classService.registerClass(userId, 1L);
                    successCount.incrementAndGet();
                } catch (Exception e) {
                    System.out.println(e);
                    failCount.incrementAndGet();
                }
            }, executorService);
            futures.add(future);
        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
        executorService.shutdown();

        Assertions.assertThat(successCount.get()).isEqualTo(30);
        Assertions.assertThat(failCount.get()).isEqualTo(30);
    }
}

