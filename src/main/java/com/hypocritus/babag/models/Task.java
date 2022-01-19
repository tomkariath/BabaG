package com.hypocritus.babag.models;

import javax.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;
    private String taskName;
    private Integer currentScore;
    private Integer completionScore;
    private Status status;
    private Boolean completed;

    public Task(){}

    public Task (String taskName, int completionScore) {
        this.taskName = taskName;
        this.currentScore =0;
        this. completionScore = completionScore;
        this.status = Status.NOT_STARTED;
        this.completed = false;
    }

    public void update (Task task) {
        if (task.getTaskName() != null){
            setTaskName(task.getTaskName());
        }
        if (task.getCurrentScore() != null){
            setCurrentScore(task.getCurrentScore());
        }
        if (task.getCompletionScore() != null){
            setCompletionScore(task.getCompletionScore());
        }
        if (task.getStatus() != null){
            setStatus(task.getStatus());
        }
        if (task.isCompleted() != null){
            setCompleted(task.isCompleted());
        }
    }

    public void reset (){
        setCurrentScore(0);
        setStatus(Status.NOT_STARTED);
        setCompleted(false);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Integer getCompletionScore() {
        return completionScore;
    }

    public void setCompletionScore(Integer completionScore) {
        this.completionScore = completionScore;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Boolean isCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completion) {
        this.completed = completion;
    }
}
