package com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@EqualsAndHashCode @ToString
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="roles_user", joinColumns = @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="roles_id", referencedColumnName = "id_role"))
    private List<Role> roles = new ArrayList<>();
}
