package com.balobanov.repositories;

import com.balobanov.models.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRepositories extends JpaRepository<Bank, Long> {}
