package classregister.service;

import classregister.domain.Class;
import classregister.domain.ClassId;
import classregister.domain.Lecture;
import classregister.domain.Member;
import classregister.repository.ClassRepository;
import classregister.repository.LectureRepository;
import classregister.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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

    public void registerClass(Long memberId, Long classId) {
        Optional<Lecture> lecture = lectureRepository.findById(classId);
        Optional<Member> member = memberRepository.findById(memberId);
        if (lecture.isPresent() && member.isPresent()){
            classRepository.save(new Class(
                    new ClassId(member.get().getId(), lecture.get().getId())
            ));
            return;
        }
        if (lecture.isEmpty()) {
            throw new NullPointerException("존재하지 않는 수업입니다.");
        }

        if (member.isEmpty()) {
            throw new NullPointerException("존재하지 않는 사용자입니다.");
        }

    }

}
