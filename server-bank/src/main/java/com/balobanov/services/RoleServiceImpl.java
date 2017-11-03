package com.balobanov.services;

import com.balobanov.models.Role;
import com.balobanov.repositories.RoleRepository;
import com.balobanov.services.abstraction.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends AbstractBaseService<Role, Long, RoleRepository> implements RoleService {

    @Override
    public List<Role> getByRoles(String role) {
        return dao.findByRoles(role);
    }
}
