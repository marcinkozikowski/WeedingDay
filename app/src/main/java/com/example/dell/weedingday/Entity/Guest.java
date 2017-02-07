package com.example.dell.weedingday.Entity;

import com.orm.SugarRecord;

/**
 * Created by Dell on 2017-01-02.
 */

public class Guest extends SugarRecord {

//    String title;
//    String edition;
//
//    public Book(){
//    }
//
//    public Book(String title, String edition){
//        this.title = title;
//        this.edition = edition;
//    }
    int UserId;
    String Name;
    String Surname;
    int PhoneNumber;
    boolean fromBride;  //Czy gosc jest Panny mlodej jesli tak to true jesli nie to false i jest pana mlodego gosc
    boolean withFriend; // czy jest osoba towarzyszaca
    boolean isSleeping; // Czy potrzebny novcleg
    String FriendName;
    String FirendSurname;

    public Guest(int userId, String name, String surname, int phoneNumber, boolean fromBride, boolean withFriend, boolean isSleeping, String friendName, String firendSurname) {
        UserId = userId;
        Name = name;
        Surname = surname;
        PhoneNumber = phoneNumber;
        this.fromBride = fromBride;
        this.withFriend = withFriend;
        this.isSleeping = isSleeping;
        FriendName = friendName;
        FirendSurname = firendSurname;
    }


    public Guest(int userId, String name, String surname, int phoneNumber, boolean fromBride, boolean withFriend, boolean isSleeping) {
        UserId = userId;
        Name = name;
        Surname = surname;
        PhoneNumber = phoneNumber;
        this.fromBride = fromBride;
        this.withFriend = withFriend;
        this.isSleeping = isSleeping;
    }

    public Guest(){

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

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public boolean isFromBride() {
        return fromBride;
    }

    public void setFromBride(boolean fromBride) {
        this.fromBride = fromBride;
    }

    public boolean isWithFriend() {
        return withFriend;
    }

    public void setWithFriend(boolean withFriend) {
        this.withFriend = withFriend;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public String getFriendName() {
        return FriendName;
    }

    public void setFriendName(String friendName) {
        FriendName = friendName;
    }

    public String getFirendSurname() {
        return FirendSurname;
    }

    public void setFirendSurname(String firendSurname) {
        FirendSurname = firendSurname;
    }
}
