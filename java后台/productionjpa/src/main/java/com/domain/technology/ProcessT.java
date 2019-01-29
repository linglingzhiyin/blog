package com.domain.technology;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProcessT {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long processTId;

    @ManyToOne()
    @JoinColumn(name = "technologyPlanId")
    private TechnologyPlan technologyPlan;


    private Integer sequence;

    private Integer quota;
}