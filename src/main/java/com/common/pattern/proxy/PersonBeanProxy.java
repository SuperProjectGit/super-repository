package com.common.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * 个人类代理
 *
 * @author subo
 * @create 2018-03-29 11:44
 **/
public class PersonBeanProxy {

    /**
     * 获取个人自身的代理
     * @param personBean
     * @return
     */
    public static PersonBean getOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new OwnerInvocationHandler(personBean));
    }

    /**
     * 获取非个人本身的代理
     * @param personBean
     * @return
     */
    public static PersonBean getNonOwnerProxy(PersonBean personBean) {
        return (PersonBean) Proxy.newProxyInstance(personBean.getClass().getClassLoader(), personBean.getClass().getInterfaces(), new NonOwnerInvocationHandler(personBean));
    }

}
