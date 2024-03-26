package classregister.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;

import java.io.Serializable;

@Embeddable
public class ClassId implements Serializable {

    @Column(name= "member_id")
    private Long memberId;

    @Column(name="lecture_id")
    private Long lectureId;

    public ClassId() {
    }

    public ClassId(Long memberId, Long lectureId) {
        this.memberId = memberId;
        this.lectureId = lectureId;
    }
}
