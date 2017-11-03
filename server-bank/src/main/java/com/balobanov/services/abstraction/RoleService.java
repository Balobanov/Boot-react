package com.balobanov.services.abstraction;

import com.balobanov.models.Role;

import java.util.List;

public interface RoleService extends BaseService<Role> {
    List<Role> getByRoles(String role);
}
