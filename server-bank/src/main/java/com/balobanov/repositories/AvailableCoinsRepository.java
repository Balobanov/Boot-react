package com.balobanov.repositories;

import com.balobanov.models.coins.AvailableCoins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableCoinsRepository extends JpaRepository<AvailableCoins, Long> {
}
