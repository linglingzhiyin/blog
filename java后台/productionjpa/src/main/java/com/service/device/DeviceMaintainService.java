package com.service.device;

import com.domain.device.DeviceFault;
import com.domain.device.DeviceMaintain;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.device.DeviceMaintainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deviceMaintain")
public class DeviceMaintainService {

    @Autowired
    private DeviceMaintainRepository deviceMaintainRepository;

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getListType(Integer page, Integer rows, DeviceMaintain deviceMaintain)
            throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<DeviceMaintain> list = deviceMaintainRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }


    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid DeviceMaintain deviceMaintain, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceMaintain deviceMaintain1 = deviceMaintainRepository.save(deviceMaintain);
        if (deviceMaintain1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备信息失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid DeviceMaintain deviceMaintain, BindingResult bindingResult) throws Exception {
        DeviceMaintain deviceFault1 = deviceMaintainRepository.save(deviceMaintain);
        if (deviceFault1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            deviceMaintainRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid DeviceMaintain deviceMaintain, BindingResult bindingResult)
            throws Exception {
        return null;
    }

    //根据设备维修编号查找
    @RequestMapping("/search_deviceMaintain_by_deviceMaintainId")
    @ResponseBody
    public EUDataGridResult searchDeviceMaintainByDeviceMaintainId(Integer page, Integer rows, String searchValue)
            throws Exception {
        List<DeviceMaintain> list = new ArrayList<DeviceMaintain>();
        DeviceMaintain temp = deviceMaintainRepository.findById(Long.parseLong(searchValue)).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<DeviceMaintain>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据设备故障编号查找
    @RequestMapping("/search_deviceMaintain_by_deviceFaultId")
    @ResponseBody
    public EUDataGridResult searchDeviceMaintainByDeviceFaultId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        DeviceMaintain d =new DeviceMaintain();
        d.setDeviceFaultId(searchValue);
        Example<DeviceMaintain> example = Example.of(d);
        List<DeviceMaintain> list = new ArrayList<DeviceMaintain>();
        DeviceMaintain temp =   deviceMaintainRepository.findOne(example).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceMaintain> pageInfo = new PageInfo<DeviceMaintain>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

}
