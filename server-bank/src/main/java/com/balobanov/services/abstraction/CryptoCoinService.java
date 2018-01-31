package com.balobanov.services.abstraction;

import com.balobanov.models.coins.CryptoCoin;

public interface CryptoCoinService extends BaseService<CryptoCoin> {

    CryptoCoin updated(CryptoCoin cryptoCoin);
    CryptoCoin getByCoinName(String name);
}
