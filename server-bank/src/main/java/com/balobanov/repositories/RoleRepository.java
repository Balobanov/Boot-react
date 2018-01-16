package com.balobanov.repositories;

import com.balobanov.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.role in :roles")
    List<Role> findByRoles(@Param("roles") String... roles);
}
