package com.balobanov.services;

import com.balobanov.models.Bank;
import com.balobanov.services.abstraction.BankService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankServiceImpl extends AbstractBaseService<Bank, Long> implements BankService{
}
