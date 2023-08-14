package com.cow.pastelitocoffe.cow.pastelitocoffe.Controllers;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.*;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Security.Jwt.JwtUtils;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.RolesService.RolesRepositoryService;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService.UsersRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UsersController {
    private UsersRepositoryService repository;
    private RolesRepositoryService repositoryRole;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    public UsersController(UsersRepositoryService repository, RolesRepositoryService repositoryRole,
                           PasswordEncoder encoder, AuthenticationManager authenticationManager,
                           JwtUtils jwtUtils){
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.encoder = encoder;
        this.repository = repository;
        this.repositoryRole = repositoryRole;
    }

    @GetMapping
    public ResponseEntity<List<DataResponseUserEntity>> findAllUsers() {
        return ResponseEntity.ok(repository.findAllUsers().stream().map(
                x -> new DataResponseUserEntity(x.getUsername(), x.getEmail(), x.getRoles())).collect(Collectors.toList())
        );
    }

    /*
     * Endpoint to create a user. Requires an admin role.
     */
    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody DataRequestUserEntity dataRequestUserEntity){
        if(repository.existsByUsername(dataRequestUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("USER").isPresent()){
            Role role = repositoryRole.findByName("USER").get();
            UserEntity user = UserEntity.builder().username(dataRequestUserEntity.username()).email(dataRequestUserEntity.email())
                    .password(encoder.encode(dataRequestUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Usuario creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*
    * This endpoint requires a previous admin in database. Require admin role.
    */
    @PostMapping("/createAdmin")
    public ResponseEntity<String> createAdmin(@RequestBody DataRequestUserEntity dataRequestUserEntity){
        if(repository.existsByUsername(dataRequestUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("ADMIN").isPresent()){
            Role role = repositoryRole.findByName("ADMIN").get();
            UserEntity user = UserEntity.builder().username(dataRequestUserEntity.username()).email(dataRequestUserEntity.email())
                    .password(encoder.encode(dataRequestUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Administrador creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*
    * Endpoint to create an employee. Requires an admin role.
    */
    @PostMapping("/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody DataRequestUserEntity dataRequestUserEntity){
        if(repository.existsByUsername(dataRequestUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("EMPLOYEE").isPresent()){
            Role role = repositoryRole.findByName("EMPLOYEE").get();
            UserEntity user = UserEntity.builder().username(dataRequestUserEntity.username()).email(dataRequestUserEntity.email())
                    .password(encoder.encode(dataRequestUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Trabajador creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*
    * Login endpoint. It is open to the public
    * */
    @PostMapping
    public ResponseEntity<AuthResponse> login(@RequestBody DataLoginUser dataLoginUser){
        boolean existUser = repository.existsByUsername(dataLoginUser.username());
        if(existUser){
            Authentication aut = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    dataLoginUser.username(),dataLoginUser.password()));
            SecurityContextHolder.getContext().setAuthentication(aut);
            String token = jwtUtils.createToken(aut);
            return ResponseEntity.ok(new AuthResponse(token));
        }
        return ResponseEntity.notFound().build();
    }
}
