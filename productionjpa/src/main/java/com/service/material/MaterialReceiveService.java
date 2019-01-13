package com.service.material;

import javax.validation.Valid;

import com.domain.material.MaterialConsume;
import com.domain.material.MaterialReceive;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.material.MaterialReceiveRepository;
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

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/materialReceive")
public class MaterialReceiveService {
	@Autowired
	private MaterialReceiveRepository materialReceiveRepository;
	
	@RequestMapping("/get/{receiveId}")
	@ResponseBody
	public MaterialReceive getItemById(@PathVariable Long receiveId) throws Exception{
		MaterialReceive cmaterial = materialReceiveRepository.findById(receiveId).get();
		return cmaterial;
	}
	

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<MaterialReceive> getData() {
		return materialReceiveRepository.findAll();
	}

	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows) throws Exception{
        EUDataGridResult result = new EUDataGridResult();
        Page<MaterialReceive> list = materialReceiveRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid MaterialReceive materialReceive, BindingResult bindingResult)
			throws Exception {
        MaterialReceive materialReceive1 = materialReceiveRepository.save(materialReceive);
        if (materialReceive1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid MaterialReceive materialReceive, BindingResult bindingResult)
			throws Exception {
        MaterialReceive deviceType1 = materialReceiveRepository.save(materialReceive);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
	}
	
	@RequestMapping(value="/updateAll")
	@ResponseBody
	private CustomResult updateAll(@Valid MaterialReceive materialReceive, BindingResult bindingResult)
			throws Exception {
        return this.update(materialReceive, bindingResult);
	}
	
	@RequestMapping(value="/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid MaterialReceive materialReceive, BindingResult bindingResult)
			throws Exception {
        MaterialReceive temp = materialReceiveRepository.findById(materialReceive.getMaterialReceiveId()).get();
        temp.setNote(materialReceive.getNote());
        if ((materialReceiveRepository.save(temp)) != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
	}
	
	@RequestMapping(value="/delete")
	@ResponseBody
	private CustomResult delete(Long id) throws Exception {
        try {
            materialReceiveRepository.deleteById(id);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
	}
	
	@RequestMapping(value="/delete_batch")
	@ResponseBody
	private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            materialReceiveRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
	}
	
	//根据物料接收id查找
	@RequestMapping("/search_materialReceive_by_receiveId")
	@ResponseBody
	public EUDataGridResult searchMaterialReceiveByReceiveId(Integer page, Integer rows
            , Long searchValue)
			throws Exception{
        List<MaterialReceive> list = new ArrayList<MaterialReceive>();
        MaterialReceive temp = materialReceiveRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialReceive> pageInfo = new PageInfo<MaterialReceive>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}

	//根据物料id查找
	@RequestMapping("/search_materialReceive_by_materialId")
	@ResponseBody
	public EUDataGridResult searchMaterialReceiveByMaterialId(Integer page, Integer rows, String searchValue)
			throws Exception{
		//EUDataGridResult result = materialReceiveService.searchMaterialReceiveByMaterialId(page, rows, searchValue);
		return null;
	}
}
