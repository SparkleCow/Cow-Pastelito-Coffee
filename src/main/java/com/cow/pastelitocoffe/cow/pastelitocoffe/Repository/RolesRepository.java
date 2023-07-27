package com.cow.pastelitocoffe.cow.pastelitocoffe.Repository;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
