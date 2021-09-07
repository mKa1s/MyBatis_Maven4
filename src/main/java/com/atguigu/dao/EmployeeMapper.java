package com.atguigu.dao;

import com.atguigu.bean.Employee;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-08-24 15:08
 */
public interface EmployeeMapper {
    @MapKey("id")
    public Map<String,Object> getEmpsByLastNameLikeReturnMap(String last_name);

    public Map<String,Object> getEmployeeByIdReturnMap(Integer id);
    public List<Employee> getEmpsByLastNameLike(String last_name);
    public Employee getEmployeeById(Integer id);
    public List<Employee> getEmployee();
    public Employee getEmployeeByIdAndName(@Param("id") Integer id, @Param("last_name") String last_name);
    public boolean addEmp(Employee employee);
    public boolean updateEmp(Employee employee);
    public boolean deleteEmpById(Integer id);
    public boolean deleteEmpByIdAndName(Integer id,String last_name);
}
