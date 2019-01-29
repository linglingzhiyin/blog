package com.service.technology;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.technology.TechnologyRequirement;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.technology.TechnologyRequirementRepository;
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
@RequestMapping("/technologyRequirement")
public class TechnologyRequirementService {

	@Autowired
	private TechnologyRequirementRepository technologyRequirementRepository;
	
	@RequestMapping("/get/{technologyRequirementId}")
	@ResponseBody
	public TechnologyRequirement getItemById(@PathVariable Long technologyRequirementId)
			throws Exception{
		return technologyRequirementRepository.findById(technologyRequirementId).get();
	}

	@RequestMapping("/getData")
	@ResponseBody
	public List<TechnologyRequirement> getData() throws Exception{
		return  technologyRequirementRepository.findAll();
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, TechnologyRequirement technologyRequirement)
			throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<TechnologyRequirement> list = technologyRequirementRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
			throws Exception {
		TechnologyRequirement temp = technologyRequirementRepository.save(technologyRequirement);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/updateAll")
	@ResponseBody
	private CustomResult updateAll(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
			throws Exception {
		TechnologyRequirement temp = technologyRequirementRepository.save(technologyRequirement);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/update_requirement")
	@ResponseBody
	private CustomResult updateNote(@Valid TechnologyRequirement technologyRequirement, BindingResult bindingResult)
			throws Exception {
		TechnologyRequirement temp = technologyRequirementRepository.findById(technologyRequirement.getTechnologyRequirementId()).get();
		temp.setRequirement(technologyRequirement.getRequirement());
		if ((technologyRequirementRepository.save(temp)) != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "更新工艺要求信息失败");
		}
	}

	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(Long[] ids) throws Exception {
		try {
			technologyRequirementRepository.deleteByIds(ids);
			return CustomResult.ok();
		} catch (Exception e) {
			throw e;
		}
	}
	
	//根据工艺要求id查找
	@RequestMapping("/search_technologyRequirement_by_technologyRequirementId")
	@ResponseBody
	public EUDataGridResult searchTechnologyRequirementByTechnologyRequirementId(Integer page,Integer rows,
			Long searchValue) throws Exception{
		List<TechnologyRequirement> list = new ArrayList<TechnologyRequirement>();
		TechnologyRequirement temp = technologyRequirementRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TechnologyRequirement> pageInfo = new PageInfo<TechnologyRequirement>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据工艺名称查找
	@RequestMapping("/search_technologyRequirement_by_technologyName")
	@ResponseBody
	public EUDataGridResult searchTechnologyRequirementByTechnologyName(Integer page, Integer rows,
			String searchValue) throws Exception{
		return  null;
	}
}
