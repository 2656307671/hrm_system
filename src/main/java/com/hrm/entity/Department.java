package com.hrm.entity;

/**
 * 部门实体类
 */
public class Department implements java.io.Serializable {
    //部门id
    private Integer deptId;
    //部门领导
    private String deptLeader;
    //部门名称
    private String deptName;

    public Department() {
    }

    public Department(Integer deptId, String deptLeader, String deptName) {
        this.deptId = deptId;
        this.deptLeader = deptLeader;
        this.deptName = deptName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + deptId +
                ", deptLeader='" + deptLeader + '\'' +
                ", deptName='" + deptName + '\'' +
                '}';
    }
}
