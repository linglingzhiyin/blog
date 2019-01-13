package com.service;

import com.repository.emp.EmployeeRepository;
import com.repository.device.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private DeviceCheckRepository deviceCheckRepository;
    @Autowired
    private DeviceFaultRepository deviceFaultRepository;
    @Autowired
    private DeviceMaintainRepository deviceMaintainRepository;
    @Autowired
    private DeviceTypeRepository deviceTypeRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    @GetMapping("/")
    public String TestService() {
        return "home";
    }
}
