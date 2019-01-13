package com.service.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.schedule.VOrder;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.schedule.VOrderRepository;
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
@RequestMapping("/order")
public class OrderService {

	@Autowired
	private VOrderRepository vOrderRepository;

	@RequestMapping("/get/{orderId}")
	@ResponseBody
	public VOrder getItemById(@PathVariable Long orderId) throws Exception{
		return vOrderRepository.findById(orderId).get();
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public List<VOrder> getData() throws Exception{
		return vOrderRepository.findAll();
	}

	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getList(Integer page, Integer rows, VOrder cOrder) throws Exception{
		EUDataGridResult result = new EUDataGridResult();
		Page<VOrder> list = vOrderRepository.findAll(new PageRequest(page - 1, rows));
		result.setRows(list.getContent());
		result.setTotal(list.getTotalElements());
		return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid VOrder order, BindingResult bindingResult) throws Exception {
		VOrder temp = vOrderRepository.save(order);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid VOrder order, BindingResult bindingResult) throws Exception {
		VOrder temp = vOrderRepository.save(order);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid VOrder order, BindingResult bindingResult) throws Exception {
		VOrder temp = vOrderRepository.save(order);
		if (temp != null) {
			return CustomResult.ok();
		} else {
			return CustomResult.build(101, "新增失败");
		}
	}
	
	@RequestMapping(value="/update_note")
	@ResponseBody
	private CustomResult updateNote(@Valid VOrder order, BindingResult bindingResult) throws Exception {
		VOrder temp = vOrderRepository.save(order);
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
			vOrderRepository.deleteByIds(ids);
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
	
	//根据订单id查找
	@RequestMapping("/search_order_by_orderId")
	@ResponseBody
	public EUDataGridResult searchOrderByOrderId(Integer page, Integer rows, Long searchValue) throws Exception{
		List<VOrder> list = new ArrayList<VOrder>();
		VOrder temp = vOrderRepository.findById(searchValue).get();
		if (temp != null)
			list.add(temp);
		//分页处理
		PageHelper.startPage(page, rows);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<VOrder> pageInfo = new PageInfo<VOrder>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}
	
	//根据客户名称查找
	@RequestMapping("/search_order_by_orderCustom")
	@ResponseBody
	public EUDataGridResult searchOrderByOrderCustom(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
	
	//根据产品名称查找
	@RequestMapping("/search_order_by_orderProduct")
	@ResponseBody
	public EUDataGridResult searchOrderByProductName(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
}
