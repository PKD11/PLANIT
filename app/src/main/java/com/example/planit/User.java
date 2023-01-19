package com.example.planit;

public class User {
    public String userid;
    public String name;
    public Integer age;
    public String email;
    public String location;
    //streak
    public Integer streak;
    public Integer longestStreak;

    public User(String userid, String name, Integer age, String email, String location) {
        this.userid = userid;
        this.name = name;
        this.age = age;
        this.email = email;
        this.location = location;
        this.streak = 2;
        this.longestStreak = 6;

    }

}
