package com.me.iseapp.Models;

public class News
{
    String shortDesc;
    String description;
    String thumbnailPath;
    String imagePath;

    public News()
    {

    }

    public News(String shortDesc, String description, String thumbnailPath, String imagePath) {
        this.shortDesc = shortDesc;
        this.description = description;
        this.thumbnailPath = thumbnailPath;
        this.imagePath = imagePath;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
