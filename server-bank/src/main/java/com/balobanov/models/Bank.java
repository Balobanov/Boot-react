package com.balobanov.models;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class Bank extends BaseModel {

    private String name;

    public Bank() {}

    public Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
