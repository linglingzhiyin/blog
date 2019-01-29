package com.domain.device;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Size;

//维修
@Data
@Entity
public class DeviceFault {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceFaultId;

    @ManyToOne()
    @JoinColumn(name = "deviceId")
    private Device device;

    @ManyToOne()
    @JoinColumn(name = "deviceCheckId")
    private DeviceCheck deviceCheck;

    //故障原因
    private String deviceFaultCause;

    //预计维修方式
    private String deviceMaintainType;

    //故障描述
    private String deviceFaultDetail;

    private Date deviceFaultDate;
    public void setDeviceFaultDate(String deviceFaultDate) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceFaultDate = sDateFormat.parse(deviceFaultDate);
    }

    @JsonIgnore
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="deviceMaintainId")
    private DeviceMaintain deviceMaintain;

}