package com.balobanov.models.dto;

import java.util.List;

public class AvailableCoinsDto {

    private List<TempCoins> coins;

    public List<TempCoins> getCoins() {
        return coins;
    }

    public void setCoins(List<TempCoins> coins) {
        this.coins = coins;
    }
}
