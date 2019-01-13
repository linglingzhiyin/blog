package  com.domain.schedule;

import com.domain.device.Device;
import com.domain.technology.ProcessT;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Work {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workId;

    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne()
    @JoinColumn(name = "processTId")
    private ProcessT processT;

    @ManyToOne()
    @JoinColumn(name = "deviceId")
    private Device device;

    private Integer rating;

  }