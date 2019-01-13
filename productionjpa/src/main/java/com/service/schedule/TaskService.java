package com.service.schedule;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.domain.schedule.Task;
import com.domain.schedule.Work;
import com.domain.util.CustomResult;
import com.domain.util.EUDataGridResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.repository.schedule.TaskRepository;
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
@RequestMapping("/task")
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@RequestMapping("/get/{taskId}")
	@ResponseBody
	public Task getItemById(@PathVariable Long taskId) throws Exception{
        return taskRepository.findById(taskId).get();
	}
	

	
	@RequestMapping("/getData")
	@ResponseBody
	public List<Task> getData() throws Exception{
		return taskRepository.findAll();
	}

	
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page, Integer rows, Task task) throws Exception{
        EUDataGridResult result = new EUDataGridResult();
        Page<Task> list = taskRepository.findAll(new PageRequest(page - 1, rows));
        result.setRows(list.getContent());
        result.setTotal(list.getTotalElements());
        return result;
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	@ResponseBody
	private CustomResult insert(@Valid Task task, BindingResult bindingResult) throws Exception{
        Task temp = taskRepository.save(task);
        if (temp != null) {
            return CustomResult.ok();
        } else {
            return CustomResult.build(101, "新增失败");
        }
	}
	
	@RequestMapping(value="/update")
	@ResponseBody
	private CustomResult update(@Valid Task task, BindingResult bindingResult) throws Exception {
       return this.insert(task,bindingResult);
	}
	
	@RequestMapping(value="/update_all")
	@ResponseBody
	private CustomResult updateAll(@Valid Task task, BindingResult bindingResult) throws Exception {
        return this.insert(task,bindingResult);
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
            taskRepository.deleteByIds(ids);
            return CustomResult.ok();
        } catch (Exception e) {
            throw e;
        }
	}
	
	//根据生产派工id查找
	@RequestMapping("/search_task_by_taskId")
	@ResponseBody
	public EUDataGridResult searchTaskByTaskId(Integer page, Integer rows, Long searchValue)
			throws Exception{
        List<Task> list = new ArrayList<Task>();
        Task temp = taskRepository.findById(searchValue).get();
        if (temp != null)
            list.add(temp);
        //分页处理
        PageHelper.startPage(page, rows);
        //创建一个返回值对象
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取记录总条数
        PageInfo<Task> pageInfo = new PageInfo<Task>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
	}
	
	//根据作业id查找
	@RequestMapping("/search_task_by_taskWorkId")
	@ResponseBody
	public EUDataGridResult searchTaskByTaskWorkId(Integer page, Integer rows, String searchValue) throws Exception{
		return null;
	}
	
	//根据生产计划id查找
	@RequestMapping("/search_task_by_taskManufactureSn")
	@ResponseBody
	public EUDataGridResult searchTaskByTaskManufactureSn(Integer page, Integer rows, String searchValue) 
			throws Exception{
		return null;
	}
}
