package com.service.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.schedule.Product;
import com.domain.technology.TechnologyRequirement;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.schedule.ProductRepository;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
@RequestMapping("/product")
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @RequestMapping("/get/{productId}")
    @ResponseBody
    public Product getItemById(@PathVariable Long productId) throws Exception {
        return productRepository.findById(productId).get();
    }



    @RequestMapping("/getData")
    @ResponseBody
    public List<Product> getData() throws Exception {
        return productRepository.findAll();
    }


    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemList(Integer page, Integer rows, Product product) throws Exception {
        EUDataGridResult result = new EUDataGridResult();
        Page<Product> list = productRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ResponseBody
    private CustomResult insert(@Valid Product product, BindingResult bindingResult) throws Exception {
        Product temp = productRepository.save(product);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    private CustomResult update(@Valid Product product, BindingResult bindingResult) throws Exception {
        Product temp = productRepository.save(product);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
    }

    @RequestMapping(value = "/update_all")
    @ResponseBody
    private CustomResult updateAll(@Valid Product product, BindingResult bindingResult) throws Exception {
        Product temp = productRepository.save(product);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
    }

    @RequestMapping(value = "/update_note")
    @ResponseBody
    private CustomResult updateNote(@Valid Product product, BindingResult bindingResult) throws Exception {
        Product temp = productRepository.findById(product.getProductId()).get();
        temp.setNote(product.getNote());
        if ((productRepository.save(temp)) != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增设备种类信息失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    private CustomResult delete(String id) throws Exception {
        //CustomResult result = productService.delete(id);
        return null;
    }

    @RequestMapping(value = "/delete_batch")
    @ResponseBody
    private CustomResult deleteBatch(Long[] ids) throws Exception {
        try {
            productRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
    }

    //根据产品id查找
    @RequestMapping("/search_product_by_productId")
    @ResponseBody
    public EUDataGridResult searchProductByProductId(Integer page, Integer rows, Long searchValue) throws Exception {
        List<Product> list = new ArrayList<Product>();
        Product temp = productRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Product> pageInfo = new PageInfo<Product>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    //根据产品名称查找
    @RequestMapping("/search_product_by_productName")
    @ResponseBody
    public EUDataGridResult searchProductByProductName(Integer page, Integer rows, String searchValue) throws Exception {
        return null;
    }

    //根据产品类型查找
    @RequestMapping("/search_product_by_productType")
    @ResponseBody
    public EUDataGridResult searchProductByProductType(Integer page, Integer rows, String searchValue)
            throws Exception {
        return null;
    }
}
