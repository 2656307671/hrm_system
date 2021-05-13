package com.hrm.service;

import com.hrm.entity.Department;

import java.util.List;

/**
 *
 */
public interface DepartmentService {

    Department queryOneById(Integer id);

    Department queryOneByName(String name);

    Department queryOneByLeader(String leader);

    List<Department> queryDeptList();

    List<Department> queryDeptsByLimitAndOffset(Integer offset,Integer limit);

    int countDepts();

    int addDept(Department department);

    int updateDeptById(Integer id,Department department);

    int deleteDeptById(Integer id);
}
