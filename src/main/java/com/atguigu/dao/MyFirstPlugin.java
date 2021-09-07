package com.atguigu.dao;


import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.annotation.Target;
import java.util.Properties;

/**
 * @author shkstart
 * @create 2021-09-04 13:53
 */
/*
* 完成插件签名
* 告诉mybatis拦截哪个插件，哪个方法，哪些参数*/

@Intercepts({
        @Signature(type= StatementHandler.class, method = "parameterize", args=java.sql.Statement.class)
})
public class MyFirstPlugin implements Interceptor {
    /*
    * intercept:拦截
    * 拦截目标对象的目标方法的执行
    */
    public Object intercept(Invocation invocation) throws Throwable {
        //动态修改sql运行的参数，以前1号现在3号
        Object target = invocation.getTarget();
        System.out.println("当前拦截的对象" + target);
        //拿到StatementHandler==>ParameterHandler==>parameterObject
        //拿到target的元数据
        MetaObject metaObject = SystemMetaObject.forObject(target);
        Object value = metaObject.getValue("parameterHandler.parameterObject");
        System.out.println("使用参数是" + value);
        //设置参数
        metaObject.setValue("parameterHandler.parameterObject", 3);
        //执行目标方法
        Object proceed = invocation.proceed();
        //返回执行后的返回值
        return proceed;
    }
    /*
    * plugin
    *   包装目标对象的：包装：为目标对象创建一个代理对象
    */
    public Object plugin(Object target) {
        //我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们的目标对象
        Object wrap = Plugin.wrap(target,this);

        //返回为当前target创建的动态代理对象
        return wrap;
    }
    /*setProperties
    *   将插件注册的property属性设置进来
    */
    public void setProperties(Properties properties) {
        System.out.println("插件配置的信息:" + properties);
    }
}
