package com.jnu.student.data;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private final int coverResourceId;

    public int getCoverResourceId() {
        return coverResourceId;
    }


    public String getTitle() {
        return title;
    }
    public void SetTitle(String title) {
        this.title= title;
    }

    public Book(String title, int coverResourceId) {
        this.title = title;
        this.coverResourceId = coverResourceId;
    }
}
