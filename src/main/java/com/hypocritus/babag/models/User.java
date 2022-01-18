package com.hypocritus.babag.models;

import javax.persistence.*;
import java.time.Month;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private long earnings;
    @OneToMany
    private List<Task> tasks;
    private int travelDays;
    private int sickDays;
    private Month birthMonth;
    //TODO achievements


    public User(String username, Month birthMonth) {
        this.username = username;
        this.birthMonth = birthMonth;
    }

    public void update (User user) {
        if (user.getUsername() != null){
            this.username = user.getUsername();
        }
        if (user.getBirthMonth() != null){
            this.birthMonth = user.getBirthMonth();
        }
    }

    public User() {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getEarnings() {
        return earnings;
    }

    public void setEarnings(long earnings) {
        this.earnings = earnings;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public int getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(int travelDays) {
        this.travelDays = travelDays;
    }

    public int getSickDays() {
        return sickDays;
    }

    public void setSickDays(int sickDays) {
        this.sickDays = sickDays;
    }

    public Month getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Month birthDay) {
        this.birthMonth = birthDay;
    }
}
