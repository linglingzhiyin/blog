package com.service.technology;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.material.Material;
import com.domain.technology.ProcessT;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.technology.ProcessTRepository;
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
@RequestMapping("/process")
public class ProcessTService {

	@Autowired
	private ProcessTRepository processTRepository;
	
	@RequestMapping("/get/{processId}")
	@ResponseBody
	public ProcessT getItemById(@PathVariable Long processId) throws Exception{
		ProcessT process = processTRepository.findById(processId).get();
		return process;
	}

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<ProcessT> getData() throws Exception{
		return processTRepository.findAll();
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, ProcessT processT) throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<ProcessT> list = processTRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid ProcessT processT, BindingResult bindingResult) throws Exception {

		ProcessT employee1 = processTRepository.save(processT);
		if (employee1 != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid ProcessT processT, BindingResult bindingResult) throws Exception {
		ProcessT deviceType1 = processTRepository.save(processT);
		if (deviceType1 != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(Long[] ids) throws Exception {
		try {
			processTRepository.deleteByIds(ids);
			return CustomResult.ok();
		} catch (Exception e) {
			throw e;
		}
	}
	
	//根据工序id查找
	@RequestMapping("/search_process_by_processId")
	@ResponseBody
	public EUDataGridResult searchProcessByProcessId(Integer page, Integer rows, Long searchValue) throws Exception{
		List<ProcessT> list = new ArrayList<ProcessT>();
		ProcessT temp = processTRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<ProcessT> pageInfo = new PageInfo<ProcessT>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据工序计划id查找
	@RequestMapping("/search_process_by_technologyPlanId")
	@ResponseBody
	public EUDataGridResult searchProcessByTechnologyPlanId(Integer page, Integer rows, String searchValue)
			throws Exception{
		//EUDataGridResult result = processService.searchProcessByTechnologyPlanId(page, rows, searchValue);
		return null;
	}
}
