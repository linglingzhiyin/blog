package com.service.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.schedule.Product;
import com.domain.schedule.Work;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.schedule.WorkRepository;
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
@RequestMapping("/work")
public class WorkService {
	
	@Autowired
	private WorkRepository workRepository;
	
	@RequestMapping("/get/{workId}")
	@ResponseBody
	public Work getItemById(@PathVariable Long workId) throws Exception{
		return workRepository.findById(workId).get();
	}
	

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<Work> getData() throws Exception{
		return workRepository.findAll();
	}

	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Work work) throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<Work> list = workRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Work work, BindingResult bindingResult) throws Exception {
		Work temp = workRepository.save(work);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid Work work, BindingResult bindingResult) throws Exception {
		Work temp = workRepository.save(work);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Work work, BindingResult bindingResult) throws Exception {
		Work temp = workRepository.save(work);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
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
			workRepository.deleteByIds(ids);
			return CustomResult.ok();
		} catch (Exception e) {
			throw e;
		}
	}
	
	//根据作业id查找
	@RequestMapping("/search_work_by_workId")
	@ResponseBody
	public EUDataGridResult searchWorkByWorkId(Integer page, Integer rows, Long searchValue) throws Exception{
		List<Work> list = new ArrayList<Work>();
		Work temp = workRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Work> pageInfo = new PageInfo<Work>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据产品名称查找
	@RequestMapping("/search_work_by_workProduct")
	@ResponseBody
	public EUDataGridResult searchWorkByWorkProduct(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
	
	//根据设备id查找
	@RequestMapping("/search_work_by_workDevice")
	@ResponseBody
	public EUDataGridResult searchWorkByWorkDevice(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
	
	//根据工序id查找
	@RequestMapping("/search_work_by_workProcess")
	@ResponseBody
	public EUDataGridResult searchWorkByWorkProcess(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
}
