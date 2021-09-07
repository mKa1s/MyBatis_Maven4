package com.atguigu.test;

import org.junit.jupiter.api.Test;

/**
 * @author shkstart
 * @create 2021-09-04 13:46
 */


public class PluginTest {

    /*
    * 插件原理
    * 1、在四大对象创建的时候不是注解返回的，而是
    *           interceptorChain.pluginAll(parameterHandler);
    * 2、获取到所有的Interceptor（拦截器）（插件需要实现的接口）；
    *           调用interceptor.plugin(target);返回target包装后的对象
    * 3、插件机制，我们可以使用插件为目标对象创建出一个代理对象：AOP（面向切面）
    *           我们的插件可以为四大对象创建出代理对象，代理对象就可以四大对象的每一个执行
    *
    * public Object pluginAll(Object target){
    *       for(Inteceptor interceptor : interceptors){
    *           target = interceptor.plugin(target);
    *       }
    *       return target;
    * }
     */
    @Test
    public void pluginTest1(){

    }
}
