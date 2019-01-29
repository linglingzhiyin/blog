package com.service.material;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.material.MaterialConsume;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.material.MaterialConsumeRepository;
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
@RequestMapping("/materialConsume")
public class MaterialConsumeService {

    @Autowired
    private MaterialConsumeRepository materialConsumeRepository;

    @RequestMapping("/get/{consumeId}")
    @ResponseBody
    public MaterialConsume getItemById(@PathVariable Long orderId) throws Exception {
        MaterialConsume materialConsume = materialConsumeRepository.findById(orderId).get();
        return materialConsume;
    }

    @RequestMapping("/getData")
    @ResponseBody
    public List<MaterialConsume> getData() throws Exception {
        List<MaterialConsume> list = materialConsumeRepository.findAll();
        return list;
    }



    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getList(Integer page, Integer rows, MaterialConsume materialConsume) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<MaterialConsume> list = materialConsumeRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid MaterialConsume materialConsume, BindingResult bindingResult)
            throws Exception {
        MaterialConsume employee1 = materialConsumeRepository.save(materialConsume);
        if (employee1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid MaterialConsume materialConsume, BindingResult bindingResult)
            throws Exception {
        MaterialConsume deviceType1 = materialConsumeRepository.save(materialConsume);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/updateAll")
    @ResponseBody
    private CustomResult updateAll(@Valid MaterialConsume materialConsume, BindingResult bindingResult)
            throws Exception {
        return this.update(materialConsume, bindingResult);
    }

    @RequestMapping(value = "/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid MaterialConsume materialConsume, BindingResult bindingResult)
            throws Exception {
        MaterialConsume temp = materialConsumeRepository.findById(materialConsume.getConsumeId()).get();
        temp.setNote(materialConsume.getNote());
        if ((materialConsumeRepository.save(temp)) != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(long id) throws Exception {
        try {
            materialConsumeRepository.deleteById(id);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            materialConsumeRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据客户id查找
    @RequestMapping("/search_materialConsume_by_consumeId")
    @ResponseBody
    public EUDataGridResult searchMaterialConsumeByConsumeId(Integer page
            , Integer rows, Long searchValue)
            throws Exception {
        List<MaterialConsume> list = new ArrayList<MaterialConsume>();
        MaterialConsume temp = materialConsumeRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsume> pageInfo = new PageInfo<MaterialConsume>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据物料id查找
    @RequestMapping("/search_materialConsume_by_materialId")
    @ResponseBody
    public EUDataGridResult searchMaterialConsumeByMaterialId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        List<MaterialConsume> list = materialConsumeRepository.findAll();
        for (MaterialConsume m : list) {
            if (m.getMaterial().getMaterialId()==searchValue)
                list.add(m);
        }
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<MaterialConsume> pageInfo = new PageInfo<MaterialConsume>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

	/*//根据作业id查找
	@RequestMapping("/search_materialConsume_by_workId")
	@ResponseBody
	public EUDataGridResult searchMaterialConsumeByWorkId(Integer page, Integer rows, String searchValue)
			throws Exception{
		EUDataGridResult result = materialConsumeService.searchMaterialConsumeByWorkId(page, rows, searchValue);
		return result;
	}*/
}

