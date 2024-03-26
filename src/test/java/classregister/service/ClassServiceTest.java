package classregister.service;

import classregister.domain.Class;
import classregister.domain.ClassId;
import classregister.domain.Lecture;
import classregister.domain.Member;
import classregister.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class ClassServiceTest {
    private ClassService classService;
    @Mock private ClassRepository classRepository;
    @Mock private MemberRepository memberRepository;
    @Mock private LectureRepository lectureRepository;

    @BeforeEach
    void beforeEach() {
        classRepository = Mockito.mock(H2ClassRepository.class);
        lectureRepository = Mockito.mock(H2LectureRepository.class);
        memberRepository = Mockito.mock(H2MemberRepository.class);
        classService = new ClassService(
                classRepository,
                lectureRepository,
                memberRepository
        );
    }

    @Test
    void test_수강신청_성공() {
        // given - when

        Mockito.doReturn(Optional.of(new Member(1L))).when(memberRepository).findById(1L);
        Mockito.doReturn(
                Optional.of(new Lecture(1L, "A", LocalDateTime.now()))
        ).when(lectureRepository).findById(1L);

        ClassId classIdObj = new ClassId(1L, 1L);
        Class classObj = new Class(classIdObj);
        Mockito.when(classRepository.save(any(Class.class))).thenReturn(classObj);

        assertTrue(
            classService.registerClass(1L, 1L) instanceof Class
        );
    }

    @Test
    void test_수강신청_실패_회원없음() {
        Mockito.doReturn(Optional.empty()).when(memberRepository).findById(1L);
        Mockito.doReturn(
                Optional.of(new Lecture(1L, "A", LocalDateTime.now()))
        ).when(lectureRepository).findById(1L);

        ClassId classIdObj = new ClassId(1L, 1L);
        Class classObj = new Class(classIdObj);
        Mockito.when(classRepository.save(any(Class.class))).thenReturn(classObj);

        assertThrows(
                NullPointerException.class,
                () ->classService.registerClass(1L, 1L)
        );
    }

    @Test
    void test_수강신청_실패_강의없음() {
        // given - when

        Mockito.doReturn(Optional.of(new Member(1L))).when(memberRepository).findById(1L);
        Mockito.doReturn(Optional.empty()).when(lectureRepository).findById(1L);

        ClassId classIdObj = new ClassId(1L, 1L);
        Class classObj = new Class(classIdObj);
        Mockito.when(classRepository.save(any(Class.class))).thenReturn(classObj);


        assertThrows(
                NullPointerException.class,
                () ->classService.registerClass(1L, 1L)
        );
    }
}