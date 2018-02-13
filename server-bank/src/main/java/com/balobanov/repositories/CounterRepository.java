package com.balobanov.repositories;

import com.balobanov.models.User;
import com.balobanov.models.counter.Counter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounterRepository extends JpaRepository<Counter, Long> {

    Counter findByUser(User user);
}
