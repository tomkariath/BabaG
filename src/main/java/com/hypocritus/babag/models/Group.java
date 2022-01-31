package com.hypocritus.babag.models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hypocritus.babag.utils.serializers.GroupUserSerializer;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "UserGroup")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;

    private String name;

    @OneToMany
    @JsonSerialize(using = GroupUserSerializer.class)
    private List<User> users;

    public Group (String name){
        this.name = name;
    }

    public Group() {

    }

    public String getName() {
        return name;
    }

    public void setName(String userName) {
        this.name = userName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void update (Group group) {
        if (group.getName() != null){
            setName(group.getName());
        }
    }
}
