package com.example.dell.weedingday.Entity;

import com.orm.SugarRecord;

/**
 * Created by Dell on 2017-01-02.
 */

public class User extends SugarRecord {

    String Name;
    String Surname;
    String Login;
    String Password;
    String email;

    public User(String name, String surname, String login, String password, String email) {
        Name = name;
        Surname = surname;
        Login = login;
        Password = password;
        this.email = email;
    }

    public User()
    {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
