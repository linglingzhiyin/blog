package com.domain.device;

import com.domain.emp.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

//维修结果
@Data
@Entity
public class DeviceMaintain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deviceMaintainId;

    private String deviceMaintainType;

    private Date deviceMaintainDate;

    public void setDeviceMaintainDate(String deviceMaintainDate) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceMaintainDate = sDateFormat.parse(deviceMaintainDate);

    }
    //维修结果
    private String deviceMaintainResult;

    @ManyToOne()
    @JoinColumn(name = "employeeId")
    private Employee employee;

    //维修费用
    private BigDecimal deviceMaintainCost;

    private String note;

    private Long deviceFaultId;
}