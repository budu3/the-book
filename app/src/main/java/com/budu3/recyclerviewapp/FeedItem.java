package com.budu3.recyclerviewapp;

public class FeedItem {

    private String title;
    private String thumbnail;
    private int image;

    public FeedItem(){

    }

    public FeedItem(String title, String thumbnail){
        this.title = title;
        this.thumbnail = thumbnail;
    }

    public FeedItem(String title, int image){
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public int getImage() {
        return image;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
