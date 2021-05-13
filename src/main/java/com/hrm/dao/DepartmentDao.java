package com.hrm.dao;

import com.hrm.entity.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 */
public interface DepartmentDao {

    //根据id查询部门
    Department selectOneById(@Param("deptId") Integer id);

    //根据名称查询部门
    Department selectOneByName(@Param("deptName") String name);

    //根据领导人查询部门
    Department selectOneByLeader(@Param("deptLeader") String leader);

    //查询所有部门
    List<Department> selectDeptList();

    //
    List<Department> selectDeptsByLimitAndOffset(@Param("offset") Integer offset,
                                                 @Param("limit") Integer limit);

    int checkDeptsExistsByNameAndleader(@Param("deptLeader") String leader,
                                        @Param("deptName") String name);

    //查询部门数量
    int countDepts();

    //插入新的部门
    int insertDept(@Param("department") Department department);

    //更新部门
    int updateDeptById(@Param("deptId") Integer id,
                   @Param("department") Department department);

    //删除部门
    int deleteDeptById(@Param("deptId") Integer id);
}
