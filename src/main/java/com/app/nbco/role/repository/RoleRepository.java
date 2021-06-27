package com.app.nbco.role.repository;

import com.app.nbco.role.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);


}
