package com.service;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RouterService {
    @RequestMapping("/order/")
//    @RequiresRoles("admin")
    @RequiresPermissions("/order/")
    public String orderFind() throws Exception {
        return "schedule/orderList";
    }

    @RequestMapping("/order/add")
    @RequiresRoles("admin")
    public String orderAdd() throws Exception {
        return "schedule/orderAdd";
    }

    @RequestMapping("/order/edit")
    @RequiresRoles("admin")
    public String orderEdit() throws Exception {
        return "schedule/orderEdit";
    }

    @RequestMapping("/product/")
    public String productFind() throws Exception {
        return "schedule/productList";
    }

    @RequestMapping("/product/add")
    @RequiresRoles("admin")
    public String productAdd() throws Exception {
        return "schedule/productAdd";
    }

    @RequestMapping("/product/edit")
    public String productEdit() throws Exception {
        return "schedule/productEdit";
    }

    @RequestMapping("/task/")
    @RequiresRoles("admin")
    public String taskFind() throws Exception {
        return "schedule/taskList";
    }

    @RequestMapping("/task/add")
    public String taskAdd() throws Exception {
        return "schedule/taskAdd";
    }

    @RequestMapping("/task/edit")
    public String taskEdit() throws Exception {
        return "schedule/taskedit";
    }

    @RequestMapping("/work/")
    public String workFind() throws Exception {
        return "schedule/workList";
    }

    @RequestMapping("/work/add")
    public String workAdd() throws Exception {
        return "schedule/workAdd";
    }

    @RequestMapping("/work/edit")
    public String workEdit() throws Exception {
        return "schedule/workedit";
    }

    @RequestMapping("/manufacture/")
    public String manufactureFind() throws Exception {
        return "schedule/manufactureList";
    }

    @RequestMapping("/manufacture/add")
    public String manufactureAdd() throws Exception {
        return "schedule/manufactureAdd";
    }

    @RequestMapping("/manufacture/edit")
    public String manufactureEdit() throws Exception {
        return "schedule/manufactureEdit";
    }

    @RequestMapping("/process/")
    public String processFind() throws Exception {
        return "technology/processList";
    }

    @RequestMapping("/process/add")
    public String processAdd() throws Exception {
        return "technology/processAdd";
    }

    @RequestMapping("/process/edit")
    public String processEdit() throws Exception {
        return "technology/processEdit";
    }

    @RequestMapping("/technologyPlan/")
    public String technologyPlanFind() throws Exception {
        return "technology/technologyPlanList";
    }

    @RequestMapping("/technologyPlan/add")
    public String technologyPlanAdd() {
        return "technology/technologyPlanAdd";
    }

    @RequestMapping("/technologyPlan/edit")
    public String technologyPlanEdit() throws Exception {
        return "technology/technologyPlanEdit";
    }

    @RequestMapping("/technologyRequirement/")
    public String technologyRequirementFind() throws Exception {
        return "technology/technologyRequirementList";
    }

    @RequestMapping("/technologyRequirement/add")
    public String technologyRequirementAdd() throws Exception {
        return "technology/technologyRequirementAdd";
    }

    @RequestMapping("/technologyRequirement/edit")
    public String technologyRequirementEdit() throws Exception {
        return "technology/technologyRequirementEdit";
    }

    @RequestMapping("/technology/")
    public String technologyFind() throws Exception {
        return "technology/technologyList";
    }

    @RequestMapping("/technology/add")
    public String technologyAdd() throws Exception {
        return "technology/technologyAdd";
    }

    @RequestMapping("/technology/edit")
    public String technologyEdit() throws Exception {
        return "technology/technologyEdit";
    }

    @RequestMapping("/materialConsume/")
    public String materialConsumeFind() throws Exception {
        return "material/materialConsumeList";
    }

    @RequestMapping("/materialConsume/add")
    public String materialConsumeAdd() throws Exception {
        return "material/materialConsumeAdd";
    }

    @RequestMapping("/materialConsume/edit")
    public String materialConsumeEdit() throws Exception {
        return "material/materialConsumeEdit";
    }

    @RequestMapping("/materialReceive/")
    public String materialReceiveFind() throws Exception {
        return "material/materialReceiveList";
    }

    @RequestMapping("/materialReceive/add")
    public String materialReceiveAdd() throws Exception {
        return "material/materialReceiveAdd";
    }

    @RequestMapping("/materialReceive/edit")
    public String materialReceiveEdit() throws Exception {
        return "material/materialReceiveEdit";
    }

    @RequestMapping("/material/")
    public String materialFind() throws Exception {
        return "material/materialList";
    }

    @RequestMapping("/material/add")
    public String materialAdd() throws Exception {
        return "material/materialAdd";
    }

    @RequestMapping("/material/edit")
    public String materialEdit() throws Exception {
        return "material/materialEdit";
    }


    @RequestMapping("/custom/")
    public String customFind() throws Exception {
        return "emp/customList";
    }

    @RequestMapping("/custom/add")
    public String customAdd() {
        return "emp/customAdd";
    }

    @RequestMapping("/custom/edit")
    public String customEdit() throws Exception {
        return "emp/customEdit";
    }

    @RequestMapping("/department/")
    public String departmentFind() throws Exception {
        return "emp/departmentList";
    }

    @RequestMapping("/department/add")
    public String departmentAdd() throws Exception {
        return "emp/departmentAdd";
    }

    @RequestMapping("/department/edit")
    public String departmentEdit() throws Exception {
        return "emp/departmentEdit";
    }

    @RequestMapping("/employee/")
    public String employeeFind() throws Exception {
        return "emp/employeeList";
    }

    @RequestMapping("/employee/add")
    public String employeeAdd() throws Exception {
        return "emp/employeeAdd";
    }

    @RequestMapping("/employee/edit")
    public String employeeEdit() throws Exception {
        return "emp/employeeEdit";
    }

    @GetMapping("/deviceCheck/")
    public String deviceCheckFind() {
        return "device/deviceCheckList";
    }

    @GetMapping("/deviceCheck/add")
    public String deviceCheckAdd() throws Exception {
        return "device/deviceCheckAdd";
    }

    @GetMapping("/deviceCheck/edit")
    public String deviceCheckEdit() throws Exception {
        return "device/deviceCheckEdit";
    }

    @GetMapping("/deviceFault/")
    public String DeviceFaultList() {
        return "device/deviceFaultList";
    }

    @GetMapping("/deviceFault/add")
    public String deviceFaultAdd() throws Exception {
        return "device/deviceFaultAdd";
    }

    @GetMapping("/deviceFault/edit")
    public String deviceFaultEdit() throws Exception {
        return "device/deviceFaultEdit";
    }


    @GetMapping("/deviceMaintain/")
    public String deviceMaintainList() {
        return "device/deviceMaintainList";
    }


    @RequestMapping("/deviceMaintain/add")
    public String deviceMaintainAdd() throws Exception {
        return "device/deviceMaintainAdd";
    }

    @RequestMapping("/deviceMaintain/edit")
    public String deviceMaintainEdit() throws Exception {
        return "device/deviceMaintainEdit";
    }

    @GetMapping("/device/")
    public String deviceList() {
        return "device/deviceList";
    }

    @GetMapping("/device/add")
    public String deviceAdd() throws Exception {
        return "device/deviceAdd";
    }

    @GetMapping("/device/edit")
    public String deviceEdit() throws Exception {
        return "device/deviceEdit";
    }

    @GetMapping("/deviceType/")
    public String deviceTypeFindAll(Model model) {
        return "device/deviceTypeList";
    }

    @GetMapping("/deviceType/add")
    public String deviceTypeAdd() throws Exception {
        return "device/deviceTypeAdd";
    }

    @GetMapping("/deviceType/edit")
    public String deviceTypeEdit() throws Exception {
        return "device/deviceTypeEdit";
    }

}
