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
    private Long earnings;
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Task> tasks;
    private Integer travelDays;
    private Integer sickDays;
    private Month birthMonth;
    private Month earningCalculatedMonth;
    //TODO achievements

    public User() {}

    public User(String username, Month birthMonth) {
        this.username = username;
        this.birthMonth = birthMonth;
    }

    public void update (User user) {
        if (user.getUsername() != null){
            setUsername(user.getUsername());
        }
        if (user.getEarnings() != null){
            setEarnings(user.getEarnings());
        }
        if (user.getTravelDays() != null){
            setTravelDays(user.getTravelDays());
        }
        if (user.getSickDays() != null){
            setSickDays(user.getSickDays());
        }
        if (user.getBirthMonth() != null){
            setBirthMonth(user.getBirthMonth());
        }
        if (user.getEarningCalculatedMonth() != null){
            setEarningCalculatedMonth(user.getEarningCalculatedMonth());
        }
    }

    public void reset () {
        setTravelDays(0);
        setSickDays(0);
    }

    public void resetEarnings () {
        setEarnings(0L);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getEarnings() {
        return earnings;
    }

    public void setEarnings(Long earnings) {
        this.earnings = earnings;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public Integer getTravelDays() {
        return travelDays;
    }

    public void setTravelDays(Integer travelDays) {
        this.travelDays = travelDays;
    }

    public Integer getSickDays() {
        return sickDays;
    }

    public void setSickDays(Integer sickDays) {
        this.sickDays = sickDays;
    }

    public Month getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(Month birthDay) {
        this.birthMonth = birthDay;
    }

    public Month getEarningCalculatedMonth() {
        return earningCalculatedMonth;
    }

    public void setEarningCalculatedMonth(Month earningCalculatedMonth) {
        this.earningCalculatedMonth = earningCalculatedMonth;
    }

    public Long getUserId() {
        return userId;
    }
}
