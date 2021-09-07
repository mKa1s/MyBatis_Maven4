package com.atguigu.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author shkstart
 * @create 2021-08-30 19:42
 */
public class Department implements Serializable{
    private Integer id;
    private String departmentName;
    private List<Employee> emps;

    public Department(Integer id) {
        this.id = id;
    }

    public List<Employee> getEmps() {
        return emps;
    }
    public void setEmps(List<Employee> emps) {
        this.emps = emps;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                '}';

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
