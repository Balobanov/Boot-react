package com.balobanov.repositories;

import com.balobanov.models.coins.CryptoCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCoinRepository extends JpaRepository <CryptoCoin, Long> {

    @Query("select cc from CryptoCoin cc join fetch cc.coin c where  c.name = ?1")
    CryptoCoin findByCoinName(String name);
}
