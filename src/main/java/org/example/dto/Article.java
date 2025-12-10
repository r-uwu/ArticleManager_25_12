package org.example.dto;

public class Article {
    private int id;
    private String title;
    private String body;
    private String dateTime;
    private String writer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateTime() {
        return dateTime;
    }

    public Article(int id, String dateTime, String title, String body, String username) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
        this.writer = username;
    }

    public String getWriter() {
        return writer;
    }
}
