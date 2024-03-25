package classregister.domain;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

@Entity
public class Class {

    @EmbeddedId
    private ClassId classId;

    public Class(ClassId classId) {
        this.classId = classId;
    }

    public ClassId getClassId() {
        return classId;
    }

    public void setClassId(ClassId classId) {
        this.classId = classId;
    }

}
