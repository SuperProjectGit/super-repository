package com.common.pattern.proxy;

/**
 * 个人类
 *
 * @author subo
 * @create 2018-03-29 11:21
 **/
public interface PersonBean {

    String getName();

    String getGender();

    String getInterests();

    int getHotOrNotRating();

    void setName(String name);

    void setGender(String gender);

    void setInterests(String interests);

    void setHotOrNotRating(int rating);

}
