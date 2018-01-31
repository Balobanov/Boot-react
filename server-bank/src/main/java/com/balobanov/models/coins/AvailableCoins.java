package com.balobanov.models.coins;

import com.balobanov.models.base.BaseModel;

import javax.persistence.Entity;

@Entity
public class AvailableCoins extends BaseModel{

    private String name;
    private String symbol;
    private String code;
    private Long coin_id;

    public Long getCoin_id() {
        return coin_id;
    }

    public void setCoin_id(Long coin_id) {
        this.coin_id = coin_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
