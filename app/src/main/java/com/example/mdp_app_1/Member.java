package com.example.mdp_app_1;

public class Member {

    private String Name;
    private String Mail;
    private Long phone;

    public Member(){

    }

    public String getName(){
        return Name;

    }

    public void setName(String name){
        Name = name;

    }

    public String getMail(){
        return Mail;

    }

    public void setMail(String mail){
        Mail = mail;

    }

    public Long getPh(){
        return phone;

    }

    public void setPh(Long phone){
        this.phone = phone;

    }

}
