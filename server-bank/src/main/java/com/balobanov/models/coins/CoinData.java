package com.balobanov.models.coins;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class CoinData extends BaseModel {

    private String date;
    private Double price;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
