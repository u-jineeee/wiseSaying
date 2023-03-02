package org.example.wiseSaying.entity;

import java.io.Serializable;

public class WiseSaying implements Serializable {
    String content;
    String author;
    int id;

    public WiseSaying(String _wiseSaying, String _author, int _id) {
        super();
        this.content = _wiseSaying;
        this.author = _author;
        this.id = _id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }
}
