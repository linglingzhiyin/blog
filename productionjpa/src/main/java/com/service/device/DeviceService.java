package com.service.device;

import com.domain.device.Device;
import com.domain.schedule.Work;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.device.DeviceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/device")
@Slf4j
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;


    @RequestMapping("/get/{deviceId}")
    @ResponseBody
    public Device getItemById(@PathVariable Long deviceId) throws Exception{
        return deviceRepository.findById(deviceId).get();
    }

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Device> deviceList = deviceRepository.findAll();
        model.addAttribute("deviceList", deviceList);
        log.info(deviceList.toString());
        return "deviceTypeList";
    }

    @GetMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Device> list = deviceRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }



    @PostMapping("/insert")
    @ResponseBody
    public CustomResult insert(@Valid Device device, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Device device1 = deviceRepository.save(device);
        if (device1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备信息失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public CustomResult update(@Valid Device device, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Device device1 = deviceRepository.save(device);
        if (device1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<Device> getData() throws Exception{
        return deviceRepository.findAll();
    }

    //根据设备id查找
    @RequestMapping("/search_device_by_deviceId")
    @ResponseBody
    public EUDataGridResult searchDeviceByDeviceId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Device> list = deviceRepository.searchDeviceByDeviceId(searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }


    //根据设备id查找

    @RequestMapping("/search_device_by_deviceName")
    @ResponseBody
    public EUDataGridResult search_device_by_deviceName(Integer page, Integer rows, String searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Device> list = deviceRepository.searchDeviceByDeviceName("%" + searchValue + "%");
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @RequestMapping("/search_device_by_deviceTypeName")
    @ResponseBody
    public EUDataGridResult search_device_by_deviceTypeName(Integer page, Integer rows, String searchValue)
            throws Exception {
        List<Device> list = new ArrayList<Device>();

        for (Device device : (List<Device>) this.getList(page, rows).getRows()) {
            if (device.getDeviceTypeId().getDeviceTypeName().contains(searchValue))
                list.add(device);
        }
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Device> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
