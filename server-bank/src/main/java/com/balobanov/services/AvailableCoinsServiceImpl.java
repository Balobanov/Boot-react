package com.balobanov.services;

import com.balobanov.models.coins.AvailableCoins;
import com.balobanov.repositories.AvailableCoinsRepository;
import com.balobanov.services.abstraction.AvailableCoinsService;
import org.springframework.stereotype.Service;

@Service
public class AvailableCoinsServiceImpl extends AbstractBaseService<AvailableCoins, Long, AvailableCoinsRepository> implements AvailableCoinsService {
}
