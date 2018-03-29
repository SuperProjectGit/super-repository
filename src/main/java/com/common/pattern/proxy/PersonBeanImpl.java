package com.common.pattern.proxy;

/**
 * 个人实现类
 *
 * @author subo
 * @create 2018-03-29 11:24
 **/
public class PersonBeanImpl implements PersonBean {

    private String name;

    private String gender;

    private String interests;

    private int rating;

    private int ratingCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public int getHotOrNotRating() {
        if (rating == 0) {
            return 0;
        }
        return (rating/ratingCount);
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void setHotOrNotRating(int rating) {
        this.rating += rating;
        ratingCount ++;
    }
}
