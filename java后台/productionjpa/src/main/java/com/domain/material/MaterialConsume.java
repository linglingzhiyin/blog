package com.domain.material;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
@Data
@Entity
public class MaterialConsume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consumeId;

    @ManyToOne()
    @JoinColumn(name = "materialId")
    private Material material;

    private Integer consumeAmount;

    private Date consumeDate;
    public void setConsumeDate(String consumeDate) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.consumeDate = sDateFormat.parse(consumeDate);
    }
    private String sender;

    private String receiver;

    private String note;
}