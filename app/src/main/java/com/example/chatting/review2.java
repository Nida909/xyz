package com.example.chatting;

public class review2 {
    String Review;
String name;

    public review2(String r,String n) {
        this.Review = r;
   this.name=n;
    }
    public String getReview() {
        return Review;
    }
    public void setReview(String nm) {
        this.Review = nm;
    }
    public String getName() {
        return name;
    }
    public void setName(String nm) {
        this.name = nm;
    }
    @Override
    public String toString()
    { return "Name : "+Review;}
}
