package com.hrm.controller;

import com.hrm.entity.Department;
import com.hrm.service.DepartmentService;
import com.hrm.util.JsonMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping(value = "/dept")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/getDeptById/{deptId}",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptById(@PathVariable("deptId") Integer id) {
        Department department=null;
        if(id>0) {
            department=departmentService.queryOneById(id);
        }
        if(department!=null) {
            return JsonMsg.success().addInfo("department",department);
        }else {
            return JsonMsg.fail().addInfo("get_dept_error","抱歉，无该部门信息。。。");
        }
    }

    /**
     * 查询所有部门名称
     * @return
     */
    @RequestMapping(value = "/getDeptName",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getDeptByName() {
        List<Department> departmentList = departmentService.queryDeptList();
        if (departmentList != null){
            return JsonMsg.success().addInfo("departmentList", departmentList);
        }
        return JsonMsg.fail();
    }

    /**
     * 分页查询
     * @param pageNo
     * @return
     */
    @RequestMapping(value = "/getDeptList",method = RequestMethod.GET)
    public ModelAndView getDeptList(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo) {
        ModelAndView mv=new ModelAndView();
        //每页显示的记录行数
        int limit = 5;
        //获取总的部门记录数
        int totalItems = departmentService.countDepts();
        //计算总的页数
        int temp = totalItems / limit;
        //计算所有部门被分成多少页
        int totalPages = (totalItems % limit== 0) ? temp : temp+1;
        //每页的起始行(offset+1)数据，如第一页(offset=0，从第1(offset+1)行数据开始)
        int offset = (pageNo - 1)*limit;
        List<Department> departments = departmentService.queryDeptsByLimitAndOffset(offset,limit);
        //将数据存入Model中，在jsp页面中可以进行展示
        mv.addObject("departments", departments);
        mv.addObject("totalItems", totalItems);
        mv.addObject("totalPages", totalPages);
        mv.addObject("curPageNo", pageNo);
        mv.setViewName("departmentPage");
        return mv;
    }

    /**
     * 查询部门信息的总页码数
     * @return
     */
    @RequestMapping(value = "/getTotalPages",method = RequestMethod.GET)
    @ResponseBody
    public JsonMsg getTotalPages() {
        //每页显示的记录行数
        int limit = 5;
        //总记录数
        int totalItems = departmentService.countDepts();
        //计算总的页数
        int temp = totalItems / limit;
        //计算所有部门被分成多少页
        int totalPages = (totalItems % limit== 0) ? temp : temp+1;
        return JsonMsg.success().addInfo("totalPages", totalPages);
    }

    /**
     * 新增部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/addDept",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg addDept(Department department) {
        int res=departmentService.addDept(department);
        if(res!=1) {
            return JsonMsg.fail().addInfo("add_dept_error","添加部门异常！！！");
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 更新部门
     * @param id
     * @param department
     * @return
     */
    @RequestMapping(value = "/updateDept/{deptId}",method = RequestMethod.PUT)
    @ResponseBody
    public JsonMsg updateDeptById(@PathVariable("deptId") Integer id,Department department) {
        int res=0;
        if(id>0) {
            res=departmentService.updateDeptById(id,department);
        }
        if(res!=1) {
            return JsonMsg.fail().addInfo("update_dept_error","更新部门失败！！！");
        }else {
            return JsonMsg.success();
        }
    }

    /**
     * 删除部门
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteDept/{deptId}",method = RequestMethod.DELETE)
    @ResponseBody
    public JsonMsg deleteDeptById(@PathVariable("deptId") Integer id) {
        int res=0;
        if(id>0) {
            res=departmentService.deleteDeptById(id);
        }
        if(res!=1) {
            return JsonMsg.fail().addInfo("del_dept_error","删除部门异常！！！");
        }else {
            return JsonMsg.success();
        }
    }
}
