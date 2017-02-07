package com.example.dell.weedingday.Entity;

import com.orm.SugarRecord;

import java.util.Date;

/**
 * Created by Dell on 2017-01-02.
 */

public class Task extends SugarRecord {

    int UserID;
    String Title;
    String About;
    Date TaskExecusionDate;
    Boolean isCompleted;

    public Task(int userID, String title, String about, Date taskExecusionDate,boolean iscompleted) {
        UserID = userID;
        Title = title;
        About = about;
        TaskExecusionDate = taskExecusionDate;
        isCompleted = iscompleted;
    }
    public Task(){

    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
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

    public Date getTaskExecusionDate() {
        return TaskExecusionDate;
    }

    public void setTaskExecusionDate(Date taskExecusionDate) {
        TaskExecusionDate = taskExecusionDate;
    }
}
