package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.RolesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.Role;


import java.util.List;
import java.util.Optional;

public interface RolesRepositoryService{

    List<Role> findAllUsers();
    Optional<Role> findByName(String name);
    Role findRoleById();
    Role createRole();
    Role deleteRole();
    Role updateRole();
}
