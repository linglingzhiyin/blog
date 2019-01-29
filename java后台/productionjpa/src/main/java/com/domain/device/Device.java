package com.domain.device;

import com.domain.emp.Employee;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@Entity
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceId;

    private String deviceName;
    //一对一
    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="deviceTypeId")
    private DeviceType deviceTypeId;

    private String deviceStatus;

    private BigDecimal devicePurchasePrice;

    private Date devicePurchaseDate;

    public void setDevicePurchaseDate(String deviceTypeWarranty) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.devicePurchaseDate = sDateFormat.parse(deviceTypeWarranty);
    }
    private Date deviceManufactureDate;
    public void setDeviceManufactureDate(String deviceTypeWarranty) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceManufactureDate = sDateFormat.parse(deviceTypeWarranty);
    }
    private Date deviceServiceLife;
    public void setDeviceServiceLife(String deviceTypeWarranty) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceServiceLife = sDateFormat.parse(deviceTypeWarranty);
    }
    private String note;
    //保管人
    @ManyToOne()
    @JoinColumn(name = "deviceKeeperId")
    private Employee deviceKeeper;
}