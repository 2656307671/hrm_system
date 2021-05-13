package com.hrm.service;

import com.hrm.entity.Employee;

import java.util.List;

/**
 *
 */
public interface EmployeeService {

    Employee queryOneById(Integer id);

    Employee queryOneByName(String name);

    List<Employee> queryByLimitAndOffset(Integer offset, Integer limit);

    int count();

    int addOne(Employee employee);

    int updateOneById(Integer id,Employee employee);

    int deleteOneById(Integer id);
}
