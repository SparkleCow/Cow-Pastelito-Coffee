package com.cow.pastelitocoffe.cow.pastelitocoffe.Repository;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
    boolean existByUsername(String username);
}
