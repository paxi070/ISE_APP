package com.example.iseapp.Models;

public class Course
{
    String title;
    String subTittle;
    String fullTittle;
    String weeklyHours;
    String minAge;
    String minWeeks;
    String classSize;
    String levelName;
    String levelCode;

    public Course(String title, String subTittle, String fullTittle, String weeklyHours, String minAge, String minWeeks, String classSize, String levelName, String levelCode) {
        this.title = title;
        this.subTittle = subTittle;
        this.fullTittle = fullTittle;
        this.weeklyHours = weeklyHours;
        this.minAge = minAge;
        this.minWeeks = minWeeks;
        this.classSize = classSize;
        this.levelName = levelName;
        this.levelCode = levelCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTittle() {
        return subTittle;
    }

    public void setSubTittle(String subTittle) {
        this.subTittle = subTittle;
    }

    public String getFullTittle() {
        return fullTittle;
    }

    public void setFullTittle(String fullTittle) {
        this.fullTittle = fullTittle;
    }

    public String getWeeklyHours() {
        return weeklyHours;
    }

    public void setWeeklyHours(String weeklyHours) {
        this.weeklyHours = weeklyHours;
    }

    public String getMinAge() {
        return minAge;
    }

    public void setMinAge(String minAge) {
        this.minAge = minAge;
    }

    public String getMinWeeks() {
        return minWeeks;
    }

    public void setMinWeeks(String minWeeks) {
        this.minWeeks = minWeeks;
    }

    public String getClassSize() {
        return classSize;
    }

    public void setClassSize(String classSize) {
        this.classSize = classSize;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelCode() {
        return levelCode;
    }

    public void setLevelCode(String levelCode) {
        this.levelCode = levelCode;
    }

    public Course() {
    }

    @Override
    public String toString() {
        return "Course{" +
                "title = '" + title + '\'' +
                ", subTittle = '" + subTittle + '\'' +
                ", fullTittle = '" + fullTittle + '\'' +
                ", weeklyHours = '" + weeklyHours + '\'' +
                ", minAge = '" + minAge + '\'' +
                ", minWeeks = '" + minWeeks + '\'' +
                ", classSize = '" + classSize + '\'' +
                ", levelName = '" + levelName + '\'' +
                ", levelCode = '" + levelCode + '\'' +
                '}';
    }

}
