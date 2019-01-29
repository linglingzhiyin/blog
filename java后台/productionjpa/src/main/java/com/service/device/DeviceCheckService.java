package com.service.device;

import com.domain.device.DeviceCheck;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.device.DeviceCheckRepository;
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
import java.util.Optional;

@Controller
@RequestMapping("/deviceCheck")
@Slf4j
public class DeviceCheckService {

    @Autowired
    private DeviceCheckRepository deviceCheckRepository;

    @GetMapping("/list")
    @ResponseBody
    public EUDataGridResult getListType(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<DeviceCheck> list = deviceCheckRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }



    @PostMapping("/insert")
    @ResponseBody
    public CustomResult insert(@Valid DeviceCheck deviceCheck, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceCheck deviceCheck1 = deviceCheckRepository.save(deviceCheck);
        if (deviceCheck1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public CustomResult update(@Valid DeviceCheck deviceCheck, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceCheck deviceCheck1 = deviceCheckRepository.save(deviceCheck);
        if (deviceCheck1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @PostMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            deviceCheckRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value="/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid DeviceCheck deviceCheck, BindingResult bindingResult) throws Exception {
        DeviceCheck deviceCheck1=deviceCheckRepository.findById(deviceCheck.getDeviceCheckId()).get();
        deviceCheck1 .setDeviceCheckResult(deviceCheck.getDeviceCheckResult());
        return this.update(deviceCheck1,bindingResult);
    }

    //根据设备种类编号查找
    @RequestMapping("/search_deviceCheck_by_deviceCheckId")
    @ResponseBody
    public EUDataGridResult searchDeviceCheckByDeviceCheckId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceCheck> list =deviceCheckRepository.searchDeviceCheckByDeviceCheckId(searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //搜索
    @RequestMapping("/search_deviceCheck_by_deviceName")
    @ResponseBody
    public EUDataGridResult searchDeviceCheckByDeviceName(Integer page, Integer rows, String searchValue)
            throws Exception{
        List<DeviceCheck> list=new ArrayList<DeviceCheck>();
        for (DeviceCheck de :deviceCheckRepository.findAll()) {
            if(de.getDevice().getDeviceName().contains(searchValue))
                list.add(de);
        }
        PageHelper.startPage(page, rows);
       //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceCheck> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}
