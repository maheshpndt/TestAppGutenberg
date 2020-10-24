package com.example.gutendex.model_section;

public class GenreListModel {
    private int image;

    public GenreListModel(int image, String title) {
        this.image = image;
        this.title = title;
    }

    @Override
    public String toString() {
        return "GenreListModel{" +
                "image=" + image +
                ", title='" + title + '\'' +
                '}';
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
}
