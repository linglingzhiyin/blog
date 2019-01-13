package  com.domain.schedule;

import com.domain.technology.Technology;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Entity
public class Manufacture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long manufactureSnId;

    private Integer launchQuantity;
	
    private Date beginDate;

    public void setBeginDate(String beginDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.beginDate = sDateFormat.parse(beginDate);
    }
    private Date endDate;
    public void setEndDate(String endDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.endDate = sDateFormat.parse(endDate);
    }

    @ManyToOne()
    @JoinColumn(name = "vOrderId")
    private VOrder vOrder;

    @ManyToOne()
    @JoinColumn(name = "technologyId")
    private Technology technology;

}