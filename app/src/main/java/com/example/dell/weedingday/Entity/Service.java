package com.example.dell.weedingday.Entity;

import com.orm.SugarRecord;

/**
 * Created by Dell on 2017-01-02.
 */

public class Service extends SugarRecord {

    int UserId;
    String Name;
    String About;
    double price;
    int Img;
    String type;

    public Service(int userId, String name, String about, double price,int img,String _type) {
        UserId = userId;
        Name = name;
        About = about;
        this.price = price;
        Img=img;
        type = _type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Service()
    {

    }

    public int getImg() {
        return Img;
    }

    public void setImg(int img) {
        Img = img;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
