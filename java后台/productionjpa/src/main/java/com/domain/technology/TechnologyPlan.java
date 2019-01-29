package com.domain.technology;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Entity
public class TechnologyPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technologyPlanId;

    @ManyToOne()
    @JoinColumn(name = "technologyId")
    private Technology technology;

    private Integer batchAmount;

    private Date startPlan;
    public void setStartPlan(String startPlan) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.startPlan = sDateFormat.parse(startPlan);
    }
    private Date endPlan;
    public void setEndPlan(String endPlan) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.endPlan = sDateFormat.parse(endPlan);
    }
    private Date commitPlan;
    public void setCommitPlan(String commitPlan) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.commitPlan = sDateFormat.parse(commitPlan);
    }
    private Date technologyPlanStart;
    public void setTechnologyPlanStart(String technologyPlanStart) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.technologyPlanStart = sDateFormat.parse(technologyPlanStart);
    }
    private Date technologyPlanEnd;
    public void setTechnologyPlanEnd(String technologyPlanEnd) throws Exception {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.technologyPlanEnd = sDateFormat.parse(technologyPlanEnd);
    }
   }