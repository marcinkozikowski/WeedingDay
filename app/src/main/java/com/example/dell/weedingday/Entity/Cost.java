package com.example.dell.weedingday.Entity;

import com.google.common.math.DoubleMath;
import com.orm.SugarRecord;

/**
 * Created by Dell on 2017-01-02.
 */

public class Cost extends SugarRecord {

    int UserId;
    String Title;
    String About;
    Double Price;

    public Cost(int userId, String title, String about, Double price) {
        UserId = userId;
        Title = title;
        About = about;
        Price = price;
    }

    public Cost(){

    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }
}
