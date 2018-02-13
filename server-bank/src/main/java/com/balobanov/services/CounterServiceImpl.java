package com.balobanov.services;

import com.balobanov.models.User;
import com.balobanov.models.counter.Counter;
import com.balobanov.repositories.CounterRepository;
import com.balobanov.services.abstraction.CounterService;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl extends AbstractBaseService<Counter, Long, CounterRepository> implements CounterService {
    @Override
    public Counter getByUser(User user) {
        return dao.findByUser(user);
    }

    @Override
    public void updateCounter(User user, int increment) {
        Counter byUser = dao.findByUser(user);
        if (byUser == null) {
            byUser = new Counter();
            byUser.setUser(user);
        }

        byUser.setCount(byUser.getCount() + increment);
        dao.save(byUser);
    }
}
