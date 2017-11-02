package com.balobanov.services;

import com.balobanov.models.User;
import com.balobanov.repositories.UserRepository;
import com.balobanov.services.abstraction.RoleService;
import com.balobanov.services.abstraction.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl extends AbstractBaseService<User, Long, UserRepository> implements UserDetailService{

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.findByEmail(username);
    }
}
