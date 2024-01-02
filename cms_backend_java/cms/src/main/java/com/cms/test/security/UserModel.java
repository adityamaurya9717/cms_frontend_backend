package com.cms.test.security;

import com.cms.test.jpa.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserModel extends User {
    private String firstName;
    private String lastname;
    private String email;
    private List<String> authority;

    public UserModel(UserEntity userEntity, Collection<GrantedAuthority> authorities) {
        super(userEntity.getFirstName(), userEntity.getPassword(),authorities);
        this.firstName = userEntity.getFirstName();
        this.lastname = userEntity.getLastName();
        this.email = userEntity.getEmail();
        if(!authorities.isEmpty()){
            this.authority = authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.toList());
        }
    }
    public UserModel(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
