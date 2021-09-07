package com.atguigu.dao;

import com.atguigu.bean.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-08-31 15:11
 */
public interface EmployeeMapperDynamicSQL {

    public List<Employee> getEmpsByConditionIf(Employee employee);

    public List<Employee> getEmpsByConditionTrim(Employee employee);

    public List<Employee> getEmpsByConditionChoose(Employee employee);

    public void updateEmp(Employee employee);

    public List<Employee> getEmpsByConditionForeach(@Param("ids") List<Integer> ids);

    public void addEmps(@Param("emps") List<Employee> emps);
}
