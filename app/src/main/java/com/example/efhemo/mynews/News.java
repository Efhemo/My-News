package com.example.efhemo.mynews;

/**
 * Created by Abiodun on 11-May-17.
 */
public class News {

    private String mImages;
    private String title;
    private String link;
    private String date;

    public News(String mImages, String title, String link, String date) {
        this.mImages = mImages;
        this.title = title;
        this.link = link;
        this.date = date;
    }

    public String getmImages() {
        return mImages;
    }

    public String getTitle() {
        return title;
    }


    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }
}
