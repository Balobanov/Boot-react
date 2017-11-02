package com.balobanov.services.abstraction;

import com.balobanov.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailService extends BaseService<User>, UserDetailsService {
}
