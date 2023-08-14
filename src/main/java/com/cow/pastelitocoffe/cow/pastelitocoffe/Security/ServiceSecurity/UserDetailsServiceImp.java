package com.cow.pastelitocoffe.cow.pastelitocoffe.Security.ServiceSecurity;

import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.Role;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Entities.Users.UserEntity;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService.UsersRepositoryService;
import com.cow.pastelitocoffe.cow.pastelitocoffe.Services.UsersService.UsersRepositoryServiceImp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

    private final UsersRepositoryService userRepository;

    public UserDetailsServiceImp(UsersRepositoryServiceImp userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findUserByUsername(username));
        if(userOpt.isPresent()){
            UserEntity userEntity = userOpt.get();
            return new User(userEntity.getUsername(),
                    userEntity.getPassword(), getAuthorities(userEntity.getRoles()));
        }
        throw new UsernameNotFoundException("Usuario no encontrado");
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
