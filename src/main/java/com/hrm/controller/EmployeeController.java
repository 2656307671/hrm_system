package com.hrm.controller;

import com.hrm.entity.Employee;
import com.hrm.service.EmployeeService;
import com.hrm.util.JsonMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 */
@Controller
@RequestMapping(value = "/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 根据id查询员工信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/getEmpById/{empId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getEmpById(@PathVariable("empId") Integer id) {
        Employee employee=employeeService.queryOneById(id);
        if(employee!=null) {
            return JsonMsg.success().addInfo("employee",employee);
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 查询输入的员工姓名是否重复
     * @param name
     * @return
     */
    @RequestMapping(value = "/checkEmpExists",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg checkEmpExists(@RequestParam("empName") String name) {
        String regName="(^[a-zA-Z0-9_-]{3,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
        if(!name.matches(regName)) {
            return JsonMsg.fail().addInfo("name_reg_error", "输入姓名为2-5位中文或6-16位英文和数字组合");
        }
        Employee employee=employeeService.queryOneByName(name);
        if(employee!=null) {
            return JsonMsg.fail().addInfo("name_reg_error", "用户名重复");
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 新增员工
     * @param employee
     * @return
     */
    @RequestMapping(value = "/addEmp",method = RequestMethod.POST)
    @ResponseBody
    public JsonMsg addEmp(Employee employee) {
        int res=employeeService.addOne(employee);
        if(res==1) {
            return JsonMsg.success();
        }else {
            return JsonMsg.fail();
        }
    }

    /**
     * 删除员工
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteEmp/{empId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteEmp(@PathVariable("empId") Integer id) {
        int res=0;
        if(id>0) {
            res=employeeService.deleteOneById(id);
        }
        if(res!=1) {
            return JsonMsg.fail().addInfo("emp_del_error", "员工删除异常");
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 更新员工
     * @param id
     * @param employee
     * @return
     */
    @RequestMapping(value = "/updateEmp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateEmp(@PathVariable("empId") Integer id,Employee employee) {
        int res=employeeService.updateOneById(id,employee);
        if(res!=1) {
            return JsonMsg.fail().addInfo("emp_update_error", "更新异常");
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 新增员工记录之后，查询最新的页数
     * @return
     */
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages() {
        //获取总的员工记录数
        int totalItems=employeeService.count();
        //计算总的页数
        int temp=totalItems/5;
        //计算所有员工记录被分成多少页
        int totalPages=(totalItems % 5 == 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages",totalPages);
    }

    /**
     * 分页查询所有员工记录
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getEmpList",method = RequestMethod.GET)
    public ModelAndView getEmp(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo) {
        ModelAndView mv=new ModelAndView();
        int limit=5;
        int offset=(pageNo-1)*limit;
        //获取总的员工记录数
        int totalItems=employeeService.count();
        //计算总的页数
        int temp=totalItems/limit;
        //计算所有员工记录被分成多少页
        int totalPages=(totalItems % limit == 0) ? temp : temp+1;
        //当前页数
        int currentPage=pageNo;
        //获取所有员工信息
        List<Employee> employeeList=employeeService.queryByLimitAndOffset(offset,limit);
        //将数据存入ModelAndView中，在jsp页面中可以进行展示
        mv.addObject("employeeList",employeeList);
        mv.addObject("totalItems",totalItems);
        mv.addObject("totalPages",totalPages);
        mv.addObject("currentPage",currentPage);
        mv.setViewName("employeePage");
        return mv;
    }
}
