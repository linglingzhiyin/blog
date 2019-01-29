package com.domain.material;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

@Data
@Entity
public class MaterialReceive {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialReceiveId;

    @ManyToOne()
    @JoinColumn(name = "materialId")
    private Material material;

    private Integer amount;

    private Date receiveDate;
    public void setReceiveDate(String receiveDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.receiveDate = sDateFormat.parse(receiveDate);
    }
    private String sender;

    private String receiver;

    private String note;
    }