package com.balobanov.services;

import com.balobanov.models.coins.CryptoCoin;
import com.balobanov.repositories.CryptoCoinRepository;
import com.balobanov.services.abstraction.CryptoCoinService;
import org.springframework.stereotype.Service;

@Service
public class CryptoCoinServiceImpl extends AbstractBaseService<CryptoCoin, Long, CryptoCoinRepository> implements CryptoCoinService {

    @Override
    public CryptoCoin updated(CryptoCoin cryptoCoin) {
        return dao.save(cryptoCoin);
    }

    @Override
    public CryptoCoin getByCoinName(String name) {
        return dao.findByCoinName(name);
    }
}
