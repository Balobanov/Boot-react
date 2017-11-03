package com.balobanov.services;

import com.balobanov.models.User;
import com.balobanov.repositories.UserRepository;
import com.balobanov.services.abstraction.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends AbstractBaseService<User, Long, UserRepository> implements UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByEmail(username);
    }
}
