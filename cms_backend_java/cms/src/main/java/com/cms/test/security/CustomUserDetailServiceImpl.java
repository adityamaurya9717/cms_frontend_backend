package com.cms.test.security;

import com.cms.test.exception.CustomException;
import com.cms.test.jpa.entity.RoleEntity;
import com.cms.test.jpa.entity.UserEntity;
import com.cms.test.jpa.repository.RoleRepository;
import com.cms.test.jpa.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public CustomUserDetailServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = "adityamaurya9717@gmail.com";
        UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Username Not Found in DB"));
        roleRepository.findById(userEntity.getRoleId());
        UserModel userModel = new UserModel(userEntity,getAuthority(userEntity.getRoleId()));
        return userModel;
    }

    private  Collection<GrantedAuthority> getAuthority(Long roleId){
     Optional<RoleEntity> optional = roleRepository.findById(roleId);
     if(!optional.isPresent()){
         return null;
     }
     Collection<GrantedAuthority>  authorities = optional.get().getAuthorityEntitySet().stream()
               .map(authority-> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
     return authorities;
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<String> authorities) {
        return authorities.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
    }

    public boolean isLoggedInUser(){
      Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
      if(authentication!=null && authentication.getCredentials() instanceof UserModel){
          return true;
      }
      return false;
    }
    public UserModel getLoggedInUser(){
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.getCredentials() instanceof UserModel){
            return (UserModel) authentication.getCredentials();

        }
        throw  new CustomException(HttpStatus.UNAUTHORIZED,"User Not Authorized");
    }
}
