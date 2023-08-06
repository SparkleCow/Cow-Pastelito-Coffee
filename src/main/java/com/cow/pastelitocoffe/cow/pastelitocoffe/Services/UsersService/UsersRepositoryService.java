package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;

import java.util.List;

public interface UsersRepositoryService {
    List<UserEntity> findAllUsers();
    UserEntity findUserById();
    UserEntity findUserByUsername(String username);
    UserEntity createUser(UserEntity userEntity);
    UserEntity deleteUser();
    UserEntity updateUser();
    boolean existsByUsername(String username);
}
