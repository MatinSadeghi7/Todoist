package com.example.matin.todoist;

import java.io.Serializable;

public class User implements Serializable {

   private String name;
    private String familyname;
    private String username;
    private String pass;
    private String email;
    private Task task;
    boolean signIn = false;




    public User( String username, String pass, String email) {

        this.username = username;
        this.pass = pass;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamilyname() {
        return familyname;
    }

    public void setFamilyname(String familyname) {
        this.familyname = familyname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
