package com.domain.device;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@Slf4j
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deviceTypeId;

    private String deviceTypeName;

    //型号
    private String deviceTypeModel;

    //规格
    private String deviceTypeSpec;

    // 供应商
    private String deviceTypeSupplier;
    //生产商
    private String deviceTypeProducer;
    //台数
    private Integer deviceTypeQuantity;
    //保修时间
    private Date deviceTypeWarranty;

    public void setDeviceTypeWarranty(String deviceTypeWarranty) throws Exception {
        SimpleDateFormat sDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.deviceTypeWarranty = sDateFormat.parse(deviceTypeWarranty);
    }
}