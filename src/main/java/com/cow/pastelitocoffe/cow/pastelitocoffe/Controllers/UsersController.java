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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collections;
import java.util.List;

@RestController
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

    /*TODO Eliminar contraseñas de las respuestas*/
    @GetMapping("/api/usuarios")
    public ResponseEntity<List<UserEntity>> findAllUsers() {
        return ResponseEntity.ok(repository.findAllUsers());
    }

    @PostMapping("/api/createUser")
    public ResponseEntity<String> createUser(@RequestBody DataUserEntity dataUserEntity){
        if(repository.existsByUsername(dataUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("USER").isPresent()){
            Role role = repositoryRole.findByName("USER").get();
            UserEntity user = UserEntity.builder().username(dataUserEntity.username()).email(dataUserEntity.email())
                    .password(encoder.encode(dataUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Usuario creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*TODO
       Implementar seguridad a este endpoint
    */
    @PostMapping("/api/createAdmin")
    public ResponseEntity<String> createAdmin(@RequestBody DataUserEntity dataUserEntity){
        if(repository.existsByUsername(dataUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("ADMIN").isPresent()){
            Role role = repositoryRole.findByName("ADMIN").get();
            UserEntity user = UserEntity.builder().username(dataUserEntity.username()).email(dataUserEntity.email())
                    .password(encoder.encode(dataUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Administrador creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*TODO
       Implementar seguridad a este endpoint
    */
    @PostMapping("/api/createEmployee")
    public ResponseEntity<String> createEmployee(@RequestBody DataUserEntity dataUserEntity){
        if(repository.existsByUsername(dataUserEntity.username())){
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        if(repositoryRole.findByName("EMPLOYEE").isPresent()){
            Role role = repositoryRole.findByName("EMPLOYEE").get();
            UserEntity user = UserEntity.builder().username(dataUserEntity.username()).email(dataUserEntity.email())
                    .password(encoder.encode(dataUserEntity.password())).roles(Collections.singletonList(role)).build();
            repository.createUser(user);
            return ResponseEntity.ok("Trabajador creado con éxito");
        }
        return ResponseEntity.internalServerError().build();
    }

    /*
    * Login endpoint. It´s enabled to the public
    * */
    @PostMapping("/api/login")
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
