package com.service.device;

import com.domain.device.DeviceType;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.device.DeviceTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/deviceType")
@Slf4j
public class DeviceTypeService {

    @Autowired
    private DeviceTypeRepository deviceTypeRepository;


    @GetMapping("/list")
    @ResponseBody
    public EUDataGridResult getListType(Integer page, Integer rows, DeviceType deviceType) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<DeviceType> list = deviceTypeRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @PostMapping("/insert")
    @ResponseBody
    public CustomResult insert(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceType deviceType1 = deviceTypeRepository.save(deviceType);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @PostMapping("/update")
    @ResponseBody
    public CustomResult update(@Valid DeviceType deviceType, BindingResult bindingResult) throws Exception {
        CustomResult result;
        DeviceType deviceType1 = deviceTypeRepository.save(deviceType);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @PostMapping("/delete_batch")
    @ResponseBody
    public CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            deviceTypeRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据设备种类编号查找
    @RequestMapping("/search_deviceType_by_deviceTypeId")
    @ResponseBody
    public EUDataGridResult searchDeviceTypeByDeviceTypeId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceType> list =
                deviceTypeRepository.searchDeviceTypeByDeviceTypeId(
                        searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据设备种类名称查找
    @RequestMapping("/search_deviceType_by_deviceTypeName")
    @ResponseBody
    public EUDataGridResult searchDeviceTypeByDeviceTypeName(Integer page, Integer rows, String searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<DeviceType> list =
                deviceTypeRepository.search_deviceType_by_deviceTypeName(
                        "%" + searchValue + "%");
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<DeviceType> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<DeviceType> getData() throws Exception {
        List<DeviceType> list = deviceTypeRepository.findAll();
        return list;
    }

    @RequestMapping("/get/{orderId}")
    @ResponseBody
    public DeviceType getItemById(@PathVariable Long orderId) throws Exception {
        Optional<DeviceType> deviceType = deviceTypeRepository.findById(orderId);
        //条件查询
/*        DeviceType d =new DeviceType();
        d.setDeviceTypeId(orderId);
        Example<DeviceType> example = Example.of(d);
        Optional<DeviceType> one = deviceTypeRepository.findOne(example);*/
        return deviceType.get();
    }
}
