package com.domain.device;

import com.domain.emp.Employee;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;

//检查
@Data
@Entity
public class DeviceCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceCheckId;
    @ManyToOne()
    @JoinColumn(name = "deviceId")
    private Device device;
    //检查人
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="employeeId")
    private Employee employee;

    //检查时间
    private Date deviceCheckDate;
    public void setDeviceCheckDate(String deviceCheckDate) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceCheckDate = sDateFormat.parse(deviceCheckDate);
    }
    // 检查结果
    private String deviceCheckResult;

}