package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.RolesService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.Role;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolesRepositoryServiceImp implements RolesRepositoryService{

    private RolesRepository repository;
    public RolesRepositoryServiceImp(RolesRepository repo){
        this.repository=repo;
    }
    @Override
    public List<Role> findAllUsers() {
        return null;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public Role findRoleById() {
        return null;
    }

    @Override
    public Role createRole() {
        return null;
    }

    @Override
    public Role deleteRole() {
        return null;
    }

    @Override
    public Role updateRole() {
        return null;
    }
}
