package com.service.emp;


import com.domain.emp.Department;
import com.domain.emp.Employee;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.emp.DepartmentRepository;
import com.repository.emp.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//@Api(value = "/employee", tags = "用户模块模块")
@Controller
@RequestMapping("/employee")
@Slf4j
@CacheConfig(cacheNames = "EmployeeService")
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @RequestMapping("/get/{employeeId}")
    @Cacheable(key = "'usersByEId'")
    @ResponseBody
    public Employee getItemById(@PathVariable Long employeeId) throws Exception {
        return employeeRepository.findById(employeeId).get();
    }


    @RequestMapping("/getData")
    @Cacheable(key = "'usersList'")
    @ResponseBody
    public List<Employee> getData() throws Exception {
        return employeeRepository.findAll();
    }



  //  @ApiOperation(value="查询所有用户", notes = "查询数据库，开始Redis缓存")
   @Cacheable(key = "'users'")
    @GetMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Employee> list = employeeRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Employee employee, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Employee employee1 = employeeRepository.save(employee);
        if (employee1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Employee employee, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Employee deviceType1 = employeeRepository.save(employee);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/updateAll")
    @ResponseBody
    private CustomResult updateAll(@Valid Employee employee, BindingResult bindingResult) throws Exception {
        return this.update(employee, bindingResult);
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(Long id) throws Exception {
        try {
            employeeRepository.deleteById(id);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            employeeRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据部门id查找
    @RequestMapping("/search_employee_by_employeeId")
    @Cacheable(key = "'usersById'")
    @ResponseBody
    public EUDataGridResult searchEmployeeByEmployeeId(Integer page, Integer rows, Long searchValue)
            throws Exception {
//分页处理
        PageHelper.startPage(page, rows);
        List<Employee> list =
                employeeRepository.searchEmployeeByEmployeeId(searchValue);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据员工名称查找
    @RequestMapping("/search_employee_by_employeeName")
    @Cacheable(key = "'usersByName'")
    @ResponseBody
    public EUDataGridResult searchEmployeeByEmployeeName(Integer page, Integer rows, String searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Employee> list = employeeRepository.searchEmployeeByEmployeeName(
                "%" + searchValue + "%");
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据部门名称查找
    @GetMapping("/search_employee_by_departmentName")
    @Cacheable(key = "'usersByDN'")
    @ResponseBody
    public EUDataGridResult searchEmployeeByDepartmentName(Integer page, Integer rows, String searchValue)
            throws Exception {

        List<Employee> list = new ArrayList<Employee>();
        for (Employee employee : (List<Employee>) this.getItemList(1, 30).getRows()) {
            if (employee.getDepartment().getDepartmentName().contains(searchValue))
                list.add(employee);
        }
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Employee> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

}
