package com.jnu.student.data;

public class Book {
    private final String title;
    private final int coverResourceId;

    public int getCoverResourceId() {
        return coverResourceId;
    }


    public String getTitle() {
        return title;
    }

    public Book(String title, int coverResourceId) {
        this.title = title;
        this.coverResourceId = coverResourceId;
    }
}
