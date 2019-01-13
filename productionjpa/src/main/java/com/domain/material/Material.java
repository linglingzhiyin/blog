package com.domain.material;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
public class Material implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long materialId;

    private String materialName;

    private String status;

    private Integer remaining;

    private String note;
}