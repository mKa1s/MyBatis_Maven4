package com.atguigu.dao;

import com.atguigu.bean.Employee;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-08-30 16:18
 */
public interface EmployeeMapperPlus {
    public Employee getEmpById(Integer id);
    public Employee getEmpAndDept(Integer id);
    public Employee getEmpByIdStep(Integer id);

    public List<Employee> getEmpsByDeptId(Integer deptId);
}
