package com.qa.data;

//pojo = plain odd java object
public class Users {

    String email;
    String password;
    String id;
    String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }



    public Users() {

    }

    public Users(String email, String password){
        this.email = email;
        this.password = password;
    }

    //getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}


