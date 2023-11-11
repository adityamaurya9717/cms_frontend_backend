package com.cms.test.security;

import com.cms.test.jpa.entity.UserEntity;
import com.cms.test.jpa.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      username = "adityamaurya9717@gmail.com";
      UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(()->new UsernameNotFoundException("Username Not Found in DB"));
      User user = new User(username,userEntity.getPassword(),mapRolesToAuthorities(Arrays.asList("USER")));
        return user;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }
}
