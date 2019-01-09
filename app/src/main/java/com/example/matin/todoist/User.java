package com.example.matin.todoist;

public class User {

   private String name;
    private String familyname;
    private String username;
    private String pass;
    private String email;
    private Task task;
    boolean signIn = false;




    public User(String name, String familyname, String username, String pass, String email) {
        this.name = name;
        this.familyname = familyname;
        this.username = username;
        this.pass = pass;
        this.email = email;
    }
}
