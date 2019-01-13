package com.service.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.schedule.Manufacture;
import com.domain.schedule.Product;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.schedule.ManufactureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/manufacture")
public class ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @RequestMapping("/get/{manufactureId}")
    @ResponseBody
    public Manufacture getItemById(@PathVariable Long manufactureId) throws Exception {
        return manufactureRepository.findById(manufactureId).get();
    }


    @RequestMapping("/getData")
    @ResponseBody
    public List<Manufacture> getData() throws Exception {
        return manufactureRepository.findAll();
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Manufacture> list = manufactureRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        Manufacture temp = manufactureRepository.save(manufacture);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        return this.insert(manufacture,bindingResult);
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Manufacture manufacture, BindingResult bindingResult) throws Exception {
        return this.insert(manufacture,bindingResult);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        return null;
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            manufactureRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据生产流水号查找
    @RequestMapping("/search_manufacture_by_manufactureSn")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureSn(Integer page, Integer rows
            , Long searchValue)
            throws Exception {
        List<Manufacture> list = new ArrayList<Manufacture>();
        Manufacture temp = manufactureRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Manufacture> pageInfo = new PageInfo<Manufacture>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据订单id查找
    @RequestMapping("/search_manufacture_by_manufactureOrderId")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureOrderId(Integer page, Integer rows, String searchValue)
            throws Exception {
        return null;
    }

    //根据工艺名称查找
    @RequestMapping("/search_manufacture_by_manufactureTechnologyName")
    @ResponseBody
    public EUDataGridResult searchManufactureByManufactureTechnologyName(Integer page, Integer rows
    ,String searchValue)throws Exception {
        return null;
    }
}
