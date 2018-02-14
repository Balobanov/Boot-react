package com.balobanov.services.abstraction;

import com.balobanov.models.User;
import com.balobanov.models.counter.Counter;

public interface CounterService extends BaseService<Counter> {

    Counter getByUser(User user);

    void updateCounter(User user, int increment);
}