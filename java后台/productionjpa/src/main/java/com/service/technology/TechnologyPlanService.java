package com.service.technology;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.material.Material;
import com.domain.technology.TechnologyPlan;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.technology.TechnologyPlanRepository;
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
@RequestMapping("/technologyPlan")
public class TechnologyPlanService {

	@Autowired
	private TechnologyPlanRepository technologyPlanRepository;
	
	@RequestMapping("/get/{technologyPlanId}")
	@ResponseBody
	public TechnologyPlan getItemById(@PathVariable Long technologyPlanId) throws Exception{
		TechnologyPlan technologyPlan = technologyPlanRepository.findById(technologyPlanId).get();
		return technologyPlan;
	}


	
	@RequestMapping("/getData")
	@ResponseBody
	public List<TechnologyPlan> getData() throws Exception{
		List<TechnologyPlan> list = technologyPlanRepository.findAll();
		return list;
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, TechnologyPlan technologyPlan)
			throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<TechnologyPlan> list = technologyPlanRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid TechnologyPlan technologyPlan, BindingResult bindingResult) throws Exception {
		CustomResult result;
		TechnologyPlan temp = technologyPlanRepository.save(technologyPlan);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/updateAll")
	@ResponseBody
	private CustomResult updateAll(@Valid TechnologyPlan technologyPlan, BindingResult bindingResult) throws Exception {
		TechnologyPlan temp = technologyPlanRepository.save(technologyPlan);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(Long[] ids) throws Exception {
		try {
			technologyPlanRepository.deleteByIds(ids);
			return CustomResult.ok();
		} catch (Exception e) {
			throw e;
		}
	}
	
	//根据工艺计划id查找
	@RequestMapping("/search_technologyPlan_by_technologyPlanId")
	@ResponseBody
	public EUDataGridResult searchTechnologyPlanByTechnologyPlanId(Integer page
			, Integer rows, Long searchValue)
			throws Exception{
		List<TechnologyPlan> list = new ArrayList<TechnologyPlan>();
		TechnologyPlan temp = technologyPlanRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TechnologyPlan> pageInfo = new PageInfo<TechnologyPlan>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据工艺名称查找
	@RequestMapping("/search_technologyPlan_by_technologyName")
	@ResponseBody
	public EUDataGridResult searchTechnologyPlanByTechnologyName(Integer page, Integer rows, String searchValue)
			throws Exception{
		return null;
	}
}
