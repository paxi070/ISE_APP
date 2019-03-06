package com.example.iseapp.Models;

public class Event
{
    String title;
    String photo;
    String description;
    String place;
    String startTime;
    String dateTime;

    public Event()
    {

    }

    public Event(String title, String photo, String description, String place, String startTime, String dateTime) {
        this.title = title;
        this.photo = photo;
        this.description = description;
        this.place = place;
        this.startTime = startTime;
        this.dateTime = dateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
