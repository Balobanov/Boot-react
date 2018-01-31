package com.balobanov.models.coins;

import com.balobanov.models.base.BaseModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class CryptoCoin extends BaseModel {

    @OneToOne(cascade = CascadeType.ALL)
    private Coin coin;

    private String dataType;
    private String baseCurrency;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CoinData> data;

    public Coin getCoin() {
        return coin;
    }

    public void setCoin(Coin coin) {
        this.coin = coin;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public List<CoinData> getData() {
        return data;
    }

    public void setData(List<CoinData> data) {
        this.data = data;
    }
}
