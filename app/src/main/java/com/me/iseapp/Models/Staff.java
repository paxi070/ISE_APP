package com.me.iseapp.Models;

public class Staff
{
    String name;
    String designation;
    String description;
    String photoURL;
    String email;

    public Staff() {
    }

    public Staff(String name, String designation, String description, String photoURL, String email) {
        this.name = name;
        this.designation = designation;
        this.description = description;
        this.photoURL = photoURL;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
