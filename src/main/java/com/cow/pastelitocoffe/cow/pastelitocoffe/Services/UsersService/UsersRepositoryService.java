package com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;

import java.util.List;

public interface UsersRepositoryService {
    List<UserEntity> findAllUsers();
    UserEntity findUserById();
    UserEntity findUserByUsername();
    UserEntity createUser();
    UserEntity deleteUser();
    UserEntity updateUser();
}
