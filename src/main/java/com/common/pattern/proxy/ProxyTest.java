package com.common.pattern.proxy;

/**
 * 测试类
 *
 * @author subo
 * @create 2018-03-29 11:48
 **/
public class ProxyTest {

    public static void main(String[] args) {
        PersonBean joe = new PersonBeanImpl();
        joe.setName("joe");
        joe.setGender("male");
        PersonBean ownerProxy = PersonBeanProxy.getOwnerProxy(joe);
        System.out.println("Name is " + ownerProxy.getName());
        ownerProxy.setInterests("bowling, go");
        System.out.println("Interests set from owner proxy");
        try {
            ownerProxy.setHotOrNotRating(10);
        } catch (Exception e) {
            System.out.println("Can't set rating from owner proxy");
        }
        System.out.println("Rating is " + ownerProxy.getHotOrNotRating());
        PersonBean nonOwnerProxy = PersonBeanProxy.getNonOwnerProxy(joe);
        System.out.println("Name is " + nonOwnerProxy.getName());
        try {
            nonOwnerProxy.setInterests("bowling ,go");
        } catch (Exception e) {
            System.out.println("Can't set interests from non owner proxy");
        }
        nonOwnerProxy.setHotOrNotRating(5);
        System.out.println("Rating set from non owner proxy");
        System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
    }

}
