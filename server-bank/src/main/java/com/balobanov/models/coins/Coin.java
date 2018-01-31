package com.balobanov.models.coins;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Coin extends BaseModel {

    @Column(unique = true)
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
