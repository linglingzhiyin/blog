package com.service.material;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.device.DeviceMaintain;
import com.domain.material.Material;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.material.MaterialRepository;
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
@RequestMapping("/material")
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    @RequestMapping("/get/{materialId}")
    @ResponseBody
    public Material getItemById(@PathVariable Long materialId) throws Exception {
        Material cmaterial = materialRepository.findById(materialId).get();
        return cmaterial;
    }



    @RequestMapping("/getData")
    @ResponseBody
    public List<Material> getData() throws Exception {
        return materialRepository.findAll();
    }

    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Material material) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Material> list = materialRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Material material, BindingResult bindingResult) throws Exception {
        CustomResult result;
        Material employee1 = materialRepository.save(material);
        if (employee1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Material material, BindingResult bindingResult) throws Exception {
        Material deviceType1 = materialRepository.save(material);
        if (deviceType1 != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Material material, BindingResult bindingResult) throws Exception {
        return this.update(material, bindingResult);
    }

    @RequestMapping(value = "/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid Material material, BindingResult bindingResult) throws Exception {
        Material temp = materialRepository.findById(material.getMaterialId()).get();
          temp.setNote(material.getNote());
        if ((materialRepository.save(temp)) != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(Long id) throws Exception {
        try {
            materialRepository.deleteById(id);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            materialRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据物料id查找
    @RequestMapping("/search_material_by_materialId")
    @ResponseBody
    public EUDataGridResult searchMaterialByMaterialId(Integer page, Integer rows, Long searchValue)
            throws Exception {
        List<Material> list = new ArrayList<Material>();
        Material temp = materialRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<Material>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据物料类型查找
    @RequestMapping("/search_material_by_materialType")
    @ResponseBody
    public EUDataGridResult searchMaterialByMaterialType(Integer page, Integer rows, String searchValue)
            throws Exception {
        //分页处理
        PageHelper.startPage(page, rows);
        List<Material> list = materialRepository.searchMaterialByMaterialType(
                "%" + searchValue + "%");
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Material> pageInfo = new PageInfo<Material>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }
}