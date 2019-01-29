package com.domain.technology;

import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Entity
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technologyId;

    private String technologyName;

    private BigDecimal price;

    private String vitalProcessPeriod;

    private Integer standardCapacity;

    private Integer overtimeStandardCapacity;

    private Integer overtimeOverfulfilCapacity;

    private Integer doubleCapacity;

    private Integer overfulfilCapacity;

   }