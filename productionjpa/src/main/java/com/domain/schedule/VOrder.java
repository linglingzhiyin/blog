package  com.domain.schedule;

import com.domain.emp.Custom;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Entity
public class VOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

    private Date orderDate;

    public void setOrderDate(String orderDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.orderDate = sDateFormat.parse(orderDate);
    }
    private Date requestDate;

    public void setRequestDate(String requestDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.requestDate = sDateFormat.parse(requestDate);
    }
    private String note;

    private Integer quantity;

    private BigDecimal unitPrice;

    private String unit;
    
    private String image;

    private String file;

    private Integer status;

    @ManyToOne()
    @JoinColumn(name = "customId")
    private Custom custom;

    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product product;


}