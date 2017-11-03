package com.balobanov.repositories;

import com.balobanov.models.PasswordRecovery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthFlowRepository extends JpaRepository<PasswordRecovery, Long>{
    PasswordRecovery findByEmail(String email);
}
