package com.hrm.dao;

import com.hrm.entity.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface EmployeeDao {

    /**
     * 根据id查询员工
     */
    Employee selectOneById(@Param("empId") Integer id);

    /**
     * 根据姓名查询员工
     */
    Employee selectOneByName(@Param("empName") String name);

    /**
     * 查询带有部门信息的员工
     */
    Employee selectWithDeptById(@Param("empId") Integer id);

    /**
     * 分页查询
     * @param offset 返回记录行的偏移量
     * @param limit 返回记录最大行数
     * @return 如offset=10，limit=5的时候，就会从数据库第11行记录开始返回5条查询结果，
     * 即范围从(offset+1)---(offset+limit)
     */
    List<Employee> selectByLimitAndOffset(@Param("offset") Integer offset,
                                          @Param("limit") Integer limit);

    /**
     * 查询总记录数
     */
    int countEmps();

    /**
     * 插入新的员工
     */
    int insertOne(@Param("employee") Employee employee);

    /**
     * 更新员工
     */
    int updateOneById(@Param("empId") Integer id,
                      @Param("employee") Employee employee);

    /**
     * 删除员工
     */
    int deleteOneById(@Param("empId") Integer id);
}
