package com.service.technology;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.technology.Technology;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.technology.TechnologyRepository;
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
@RequestMapping("/technology")
public class TechnologyService {

	@Autowired
	private TechnologyRepository technologyRepository;
	
	@RequestMapping("/get/{technologyId}")
	@ResponseBody
	public Technology getItemById(@PathVariable Long technologyId) throws Exception{
		return technologyRepository.findById(technologyId).get();
	}

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<Technology> getData() throws Exception{
		return  technologyRepository.findAll();
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Technology technology) throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<Technology> list = technologyRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Technology technology, BindingResult bindingResult) throws Exception {
		Technology temp = technologyRepository.save(technology);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增设备种类信息失败");
		}
	}

	@RequestMapping(value="/updateAll")
	@ResponseBody
	private CustomResult updateAll(@Valid Technology technology, BindingResult bindingResult) throws Exception {
		Technology temp = technologyRepository.save(technology);
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
			technologyRepository.deleteByIds(ids);
			return CustomResult.ok();
		} catch (Exception e) {
			throw e;
		}
	}
	
	//根据工艺id查找
	@RequestMapping("/search_technology_by_technologyId")
	@ResponseBody
	public EUDataGridResult searchTechnologyByTechnologyId(Integer page, Integer rows
			, Long searchValue)
			throws Exception{
		List<Technology> list = new ArrayList<Technology>();
		Technology temp = technologyRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<Technology> pageInfo = new PageInfo<Technology>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据工艺名称查找
	@RequestMapping("/search_technology_by_technologyName")
	@ResponseBody
	public EUDataGridResult searchTechnologyByTechnologyName(Integer page, Integer rows, String searchValue)
			throws Exception{
		return null;
	}
	
}
