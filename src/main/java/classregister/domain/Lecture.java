package classregister.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name="start_time")
    private LocalDateTime startTime;

    @Column
    private Long quantity;

    public Lecture(){

    }

    public Lecture(String name, LocalDateTime startTime, Long quantity){
        this.name = name;
        this.startTime = startTime;
        this.quantity = quantity;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public Long getQuantity() { return quantity;}

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setQuantity(Long quantity) { this.quantity = quantity; }

}
