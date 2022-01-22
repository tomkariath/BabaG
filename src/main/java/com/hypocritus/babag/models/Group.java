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
    @OneToMany
    @JsonSerialize(using = GroupUserSerializer.class)
    private List<User> users;

    public Long getGroupId() {
        return groupId;
    }

    /*public List<User> getUsers() {
        return users;
    }*/
}
