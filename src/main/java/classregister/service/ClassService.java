package classregister.service;

import classregister.domain.Class;
import classregister.domain.ClassId;
import classregister.domain.Lecture;
import classregister.domain.Member;
import classregister.repository.ClassRepository;
import classregister.repository.LectureRepository;
import classregister.repository.MemberRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ClassService {

    @Autowired ClassRepository classRepository;
    @Autowired LectureRepository lectureRepository;
    @Autowired MemberRepository memberRepository;

    public ClassService(ClassRepository classRepository,
                        LectureRepository lectureRepository,
                        MemberRepository memberRepository
                        ) {
        this.classRepository = classRepository;
        this.lectureRepository = lectureRepository;
        this.memberRepository = memberRepository;
    }

    public Class registerClass(Long memberId, Long lectureId) {
        Optional<Lecture> lecture = lectureRepository.findById(lectureId);
        Optional<Member> member = memberRepository.findById(memberId);
        if (lecture.isEmpty()) {
            throw new NullPointerException("존재하지 않는 수업입니다.");
        }

        if (member.isEmpty()) {
            throw new NullPointerException("존재하지 않는 사용자입니다.");
        }

        if (lectureRepository.isLimitedOrDecrease(lecture.get().getId())) {
            throw new NullPointerException("사용자 제한이 있습니다.");
        }

        Class saveObject = new Class(
            new ClassId(member.get().getId(), lecture.get().getId())
        );

        return classRepository.save(saveObject);
    }

    public void registerMember(){
        this.memberRepository.save();
    }

    public void registerLecture(String name){
        this.lectureRepository.save(name, LocalDateTime.now());
    }
}
