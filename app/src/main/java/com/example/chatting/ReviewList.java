package com.example.chatting;

public class ReviewList {
    String Review;


    public ReviewList(String r) {
        this.Review = r;

    }
    public String getName() {
        return Review;
    }
    public void setName(String nm) {
        this.Review = nm;
    }

    @Override
    public String toString()
    { return "Name : "+Review;}
}
