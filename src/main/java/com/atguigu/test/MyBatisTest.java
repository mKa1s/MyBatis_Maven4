package com.atguigu.test;

import com.atguigu.bean.Department;
import com.atguigu.bean.Employee;
import com.atguigu.dao.DepartmentMapper;
import com.atguigu.dao.EmployeeMapper;
import com.atguigu.dao.EmployeeMapperDynamicSQL;
import com.atguigu.dao.EmployeeMapperPlus;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author shkstart
 * @create 2021-08-23 15:21
 */
public class MyBatisTest {
    public SqlSessionFactory getSqlSessionFactory() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }


    //接口式编程推荐
    @Test
    public void myTest1() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();

        SqlSession session = sqlSessionFactory.openSession();

        try{
            EmployeeMapper dao = session.getMapper(EmployeeMapper.class);
            PageHelper.startPage(1,5);

            List<Employee> employee = dao.getEmployee();
            PageInfo<Employee> info = new PageInfo<Employee>(employee);
            for(Employee emp : employee) {
                System.out.println(emp);
            }
            System.out.println("当前页码："+info.getPageNum());
            System.out.println("总记录数:" + info.getTotal());
        }finally {
            session.close();
        }
    }

    @Test
    public void test02() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //true则表示会自动提交，不写或者false为手动提交
        SqlSession session = sqlSessionFactory.openSession(true);

        try{
            EmployeeMapper dao = session.getMapper(EmployeeMapper.class);
            //查
            //Employee employee = dao.getEmployeeByIdAndName(3,"asd");
            //System.out.println(employee);
            //增
//            Employee employee = new Employee(null,"asd","abc@qq.com","男");
//            boolean flag = dao.addEmp(employee);
//            System.out.println(employee.getId());
            //删
            //dao.deleteEmpById(2);
            dao.deleteEmpByIdAndName(3,"asd");

            //改
            //Employee employee = new Employee(1,"asd","abc@qq.com","男");
            //boolean flag = dao.updateEmp(employee);

            //List
//            List<Employee> employees = dao.getEmpsByLastNameLike("%s%");
//            for(Employee employee : employees)
//                System.out.println(employee);


            //map
            Map<String, Object> employee = dao.getEmployeeByIdReturnMap(1);
            System.out.println(employee);

            //map用id做主键
            Map<String, Object> employees = dao.getEmpsByLastNameLikeReturnMap("%s%");
            System.out.println(employees);

        }finally {
            session.close();
        }
    }

    @Test
    public void test03() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            EmployeeMapperPlus dao = session.getMapper(EmployeeMapperPlus.class);
//            Employee employee = dao.getEmpById(1);
//            System.out.println(employee);

//            Employee employee = dao.getEmpAndDept(1);
//            System.out.println(employee);
//            System.out.println(employee.getDept());

            Employee employee = dao.getEmpByIdStep(1);
            System.out.println(employee.getEmail());
            //System.out.println(employee.getDept());
        }finally {
            session.close();
        }
    }

    @Test
    public void test04() throws IOException{
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            DepartmentMapper dao = session.getMapper(DepartmentMapper.class);
            Department department = dao.getDeptByIdStep(1);
            System.out.println(department);
            System.out.println(department.getEmps());

        }finally {
            session.close();
        }
    }

    @Test
    public void testDynamicSql() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try{
            EmployeeMapperDynamicSQL dao = session.getMapper(EmployeeMapperDynamicSQL.class);
//            Employee employee = new Employee(null,"%s%","asdw@qq.com",null);
//            List<Employee> list = dao.getEmpsByConditionTrim(employee);
//            for(Employee e : list)
//                System.out.println(e);

//            Employee employee = new Employee(null,"%s%","asdw@qq.com",null);
//            List<Employee> list = dao.getEmpsByConditionChoose(employee);
//            for(Employee e : list)
//                System.out.println(e);

//            //测试set标签
//            Employee employee = new Employee(1,"wad","asdw@qq.com",null);
//            dao.updateEmp(employee);

//            List<Employee> list = dao.getEmpsByConditionForeach(Arrays.asList(1,2,3,4));
//            for(Employee e : list)
//                System.out.println(e);
            List<Employee> emps = new ArrayList<Employee>();
            emps.add(new Employee(null,"sim","asd","1",new Department(1)));
            emps.add(new Employee(null,"sxx","asasd","0",new Department(2)));
            dao.addEmps(emps);
            session.commit();
        }finally {
            session.close();
        }
    }

    @Test
    public void testFirstLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        try {
            EmployeeMapper dao = session.getMapper(EmployeeMapper.class);
            Employee emp1 = dao.getEmployeeById(1);
            dao.deleteEmpById(3);
            Employee emp2 = dao.getEmployeeById(1);
            System.out.println(emp1 == emp2);
        }finally {

        }
    }

    @Test
    public void testSecondLevelCache() throws IOException {
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        SqlSession session = sqlSessionFactory.openSession();
        SqlSession session2 = sqlSessionFactory.openSession();
        try{
            EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
            EmployeeMapper mapper2 = session2.getMapper(EmployeeMapper.class);
            mapper.getEmployeeById(1);
            session.close();
            mapper2.getEmployeeById(1);
        }finally {
            session2.close();
            session.close();
        }
    }

}
