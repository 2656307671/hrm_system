package com.hrm.service.impl;

import com.hrm.dao.DepartmentDao;
import com.hrm.entity.Department;
import com.hrm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department queryOneById(Integer id) {
        return departmentDao.selectOneById(id);
    }

    @Override
    public Department queryOneByName(String name) {
        return departmentDao.selectOneByName(name);
    }

    @Override
    public Department queryOneByLeader(String leader) {
        return departmentDao.selectOneByLeader(leader);
    }

    @Override
    public List<Department> queryDeptList() {
        return departmentDao.selectDeptList();
    }

    @Override
    public List<Department> queryDeptsByLimitAndOffset(Integer offset, Integer limit) {
        return departmentDao.selectDeptsByLimitAndOffset(offset,limit);
    }

    @Override
    public int countDepts() {
        return departmentDao.countDepts();
    }

    @Override
    public int addDept(Department department) {
        return departmentDao.insertDept(department);
    }

    @Override
    public int updateDeptById(Integer id, Department department) {
        return departmentDao.updateDeptById(id,department);
    }

    @Override
    public int deleteDeptById(Integer id) {
        return departmentDao.deleteDeptById(id);
    }
}
