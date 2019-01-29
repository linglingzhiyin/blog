package com.service.emp;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.emp.Custom;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.emp.CustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/custom")
public class CustomService {

	@Autowired
	private CustomRepository customRepository;
	
	@RequestMapping("/get/{customId}")
	@ResponseBody
	public Custom getItemById(@PathVariable Long customId) throws Exception{
        return customRepository.findById(customId).get();
    }

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<Custom> getData() throws Exception{
        return customRepository.findAll();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Custom custom) throws Exception{
        EUDataGridResult result = new EUDataGridResult();
        Page<Custom> list = customRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Custom custom, BindingResult bindingResult) throws Exception {
        Custom temp = customRepository.save(custom);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid Custom custom, BindingResult bindingResult) throws Exception {
		return this.insert(custom,bindingResult);
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Custom custom, BindingResult bindingResult) throws Exception {

        return this.insert(custom,bindingResult);
	}
	
	@RequestMapping(value="/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid Custom custom, BindingResult bindingResult) throws Exception {

        return this.insert(custom,bindingResult);
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(String id) throws Exception {
		return null;
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            customRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
	}
	
	@RequestMapping(value="/change_status")
	@ResponseBody
	public CustomResult changeStatus(String[] ids) throws Exception{
		return null;
	}
	
	//根据客户id查找
	@RequestMapping("/search_custom_by_customId")
	@ResponseBody
	public EUDataGridResult searchCustomByCustomId(Integer page, Integer rows, Long searchValue)
			throws Exception{
        List<Custom> list = new ArrayList<Custom>();
        Custom temp = customRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Custom> pageInfo = new PageInfo<Custom>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	
	//根据客户名查找
	@RequestMapping("/search_custom_by_customName")
	@ResponseBody
	public EUDataGridResult searchCustomByCustomName(Integer page, Integer rows, String searchValue) 
			throws Exception{
		return null;
	}
	
}
