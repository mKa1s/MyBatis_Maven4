package com.atguigu.dao;

import com.atguigu.bean.Department;
import com.atguigu.bean.Employee;

import java.util.List;

/**
 * @author shkstart
 * @create 2021-08-30 20:30
 */
public interface DepartmentMapper {
    public Department getDeptById(Integer id);

    public Department getDeptByIdPlus(Integer id);

    public Department getDeptByIdStep(Integer id);
}
