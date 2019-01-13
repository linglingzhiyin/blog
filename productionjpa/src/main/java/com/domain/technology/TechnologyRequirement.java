package com.domain.technology;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
//工艺要求
public class TechnologyRequirement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technologyRequirementId;

    @ManyToOne()
    @JoinColumn(name = "technologyId")
    private Technology technology;

    private String requirement;

    private Date addTime;
    public void setAddTime(String addTime) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.addTime = sDateFormat.parse(addTime);
    }
    private Date reviseTime;
    public void setReviseTime(String reviseTime) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.reviseTime = sDateFormat.parse(reviseTime);
    }

  }