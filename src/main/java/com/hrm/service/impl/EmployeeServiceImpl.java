package com.hrm.service.impl;

import com.hrm.dao.EmployeeDao;
import com.hrm.entity.Employee;
import com.hrm.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee queryOneById(Integer id) {
        return employeeDao.selectOneById(id);
    }

    @Override
    public Employee queryOneByName(String name) {
        return employeeDao.selectOneByName(name);
    }

    @Override
    public List<Employee> queryByLimitAndOffset(Integer offset, Integer limit) {
        return employeeDao.selectByLimitAndOffset(offset,limit);
    }

    @Override
    public int count() {
        return employeeDao.countEmps();
    }

    @Override
    public int addOne(Employee employee) {
        return employeeDao.insertOne(employee);
    }

    @Override
    public int updateOneById(Integer id, Employee employee) {
        return employeeDao.updateOneById(id,employee);
    }

    @Override
    public int deleteOneById(Integer id) {
        return employeeDao.deleteOneById(id);
    }
}
