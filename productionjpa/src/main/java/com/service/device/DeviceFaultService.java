package com.service.device;

import com.domain.device.DeviceFault;
import com.domain.device.DeviceMaintain;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.device.DeviceFaultRepository;
import com.repository.device.DeviceMaintainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deviceFault")
@Slf4j
public class DeviceFaultService {

    @Autowired
    private DeviceFaultRepository deviceFaultRepository;

    @Autowired
    private DeviceMaintainRepository deviceMaintainRepository;

    @GetMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<DeviceFault> list = deviceFaultRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping("/get/{orderId}")
    @ResponseBody
    public DeviceFault getItemById(@PathVariable Long orderId) throws Exception {
        return deviceFaultRepository.findById(orderId).get();
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<DeviceFault> getData() throws Exception {
        return deviceFaultRepository.findAll();
    }

    @PostMapping("/insert")
    @ResponseBody
    public CustomResult insert(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {
        DeviceMaintain deviceMaintain=new DeviceMaintain();
        deviceMaintain.setDeviceMaintainType(deviceFault.getDeviceMaintainType());
        deviceMaintain.setDeviceFaultId(deviceFault.getDeviceFaultId());
        deviceMaintainRepository.save(deviceMaintain);
        deviceFault.setDeviceMaintain(deviceMaintain);
        DeviceFault deviceFault1 = deviceFaultRepository.save(deviceFault);
        if (deviceFault1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备信息失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public CustomResult update(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceFault deviceFault1 = deviceFaultRepository.save(deviceFault);
        if (deviceFault1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid DeviceFault deviceFault, BindingResult bindingResult) throws Exception {

        DeviceFault d=deviceFaultRepository.findById(deviceFault.getDeviceFaultId()).get();
        d.setDeviceFaultDetail(deviceFault.getDeviceFaultDetail());
        DeviceFault deviceFault1 = deviceFaultRepository.save(d);
        if (deviceFault1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }


    //根据设备id查找
    @RequestMapping("/search_deviceFault_by_deviceFaultId")
    @ResponseBody
    public EUDataGridResult searchDeviceFaultByDeviceFaultId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceFault> list = deviceFaultRepository.searchDeviceFaultByDeviceFaultId(searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceFault> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @PostMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            deviceFaultRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据设备id查找

    @RequestMapping("/search_deviceFault_by_deviceName")
    @ResponseBody
    public EUDataGridResult search_DeviceFault_by_DeviceFaultName(Integer page, Integer rows, String searchValue)
            throws Exception {

        List<DeviceFault> list = new ArrayList<DeviceFault>();
        for (DeviceFault d :deviceFaultRepository.findAll()) {
            if (d.getDevice().getDeviceName().contains(searchValue))
                list.add(d);
        }
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceFault> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
