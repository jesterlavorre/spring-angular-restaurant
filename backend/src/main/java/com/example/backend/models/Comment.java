package com.example.backend.models;

public class Comment {
    String text;
    String insertTime;
    String commentBy;
    String rating;

    public Comment() {
    }

    public Comment(String text, String insertTime, String commentBy, String rating) {
        this.text = text;
        this.insertTime = insertTime;
        this.commentBy = commentBy;
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public String getCommentBy() {
        return commentBy;
    }

    public void setCommentBy(String commentBy) {
        this.commentBy = commentBy;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
