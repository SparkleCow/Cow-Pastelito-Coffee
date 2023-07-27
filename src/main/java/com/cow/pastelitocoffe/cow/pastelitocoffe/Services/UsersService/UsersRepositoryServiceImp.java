package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.UsersRepository;

import java.util.List;

public class UsersRepositoryServiceImp implements UsersRepositoryService{
    private UsersRepository repository;
    public UsersRepositoryServiceImp(UsersRepository repo){
        this.repository = repo;
    }
    @Override
    public List<UserEntity> findAllUsers() {
        return repository.findAll();
    }

    @Override
    public UserEntity findUserById() {
        return null;
    }

    @Override
    public UserEntity findUserByUsername() {
        return null;
    }

    @Override
    public UserEntity createUser() {
        return null;
    }

    @Override
    public UserEntity deleteUser() {
        return null;
    }

    @Override
    public UserEntity updateUser() {
        return null;
    }
}
