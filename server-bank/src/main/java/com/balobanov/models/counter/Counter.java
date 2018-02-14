package com.balobanov.models.counter;

import com.balobanov.models.User;
import com.balobanov.models.base.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Counter extends BaseModel {

    private int count;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.MERGE})
    private User user;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
