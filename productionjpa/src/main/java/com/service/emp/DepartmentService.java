package com.service.emp;


import com.domain.emp.Department;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.emp.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/department")
@Slf4j
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/get/{departmentId}")
    @ResponseBody
    public Department getItemById(@PathVariable Long departmentId) throws Exception {
        return departmentRepository.findById(departmentId).get();
    }


    @RequestMapping("/getData")
    @ResponseBody
    public List<Department> getData() throws Exception {
        return departmentRepository.findAll();
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Department department) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Department> list = departmentRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Department department, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Department department1 = departmentRepository.save(department);
        if (department1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Department department, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Department deviceType1 = departmentRepository.save(department);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/updateAll")
    @ResponseBody
    private CustomResult updateAll(@Valid Department department, BindingResult bindingResult) throws Exception {
        return this.update(department, bindingResult);
    }

    @RequestMapping(value = "/updateNote")
    @ResponseBody
    private CustomResult updateNote(@Valid Department department, BindingResult bindingResult) throws Exception {
        Department departmentTemp=departmentRepository.findById(department.getDepartmentId()).get();
        departmentTemp.setNote(department.getNote());
        return this.update(departmentTemp, bindingResult);
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(Long id) throws Exception {
        try {
            departmentRepository.deleteById(id);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            departmentRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据部门id查找
    @RequestMapping("/search_department_by_departmentId")
    @ResponseBody
    public EUDataGridResult searchDepartmentByDepartmentId(Integer page, Integer rows, Long searchValue)
            throws Exception {
//分页处理
        PageHelper.startPage(page, rows);
        List<Department> list =
                departmentRepository.searchDepartmentByDepartmentId(searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据部门名称查找
    @RequestMapping("/search_department_by_departmentName")
    @ResponseBody
    public EUDataGridResult searchDepartmentByDepartmentName(Integer page, Integer rows, String searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Department> list =departmentRepository.searchDepartmentByDepartmentName(
                "%" + searchValue + "%");
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Department> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

}
