package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Repository.UsersRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
    public UserEntity findUserByUsername(String username) {
        Optional<UserEntity> userOpt = repository.findByUsername(username);
        return userOpt.orElseThrow();
    }

    @Override
    public UserEntity createUser(UserEntity userEntity) {
        return repository.save(userEntity);
    }

    @Override
    public UserEntity deleteUser() {
        return null;
    }

    @Override
    public UserEntity updateUser() {
        return null;
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
