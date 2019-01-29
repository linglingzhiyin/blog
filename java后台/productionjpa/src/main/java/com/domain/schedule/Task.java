package  com.domain.schedule;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @ManyToOne()
    @JoinColumn(name = "manufactureSnId")
    private Manufacture manufacture;

    @ManyToOne()
    @JoinColumn(name = "workId")
    private Work work;

    private Integer taskQuantity;

    private Long workingHours;
}