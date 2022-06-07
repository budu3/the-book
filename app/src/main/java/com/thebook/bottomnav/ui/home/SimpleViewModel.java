package com.thebook.bottomnav.ui.home;

public class SimpleViewModel {
    private String poster;
    private String title;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    private int image;

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public String getTitle() {
        return title;
    }
    /*
    public void ViewModel(String poster, String title){
            this.poster = poster;
            this.title = title;
    }
    */
    public void SimpleViewModel(String title, int image){
        this.image = image;
        this.title = title;
    }

}
