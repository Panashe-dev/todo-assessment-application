package com.lupart.technologies.TODO.service.impl;

import com.lupart.technologies.TODO.auth.showUserDetails;
import com.lupart.technologies.TODO.dto.UserDTO;
import com.lupart.technologies.TODO.entity.User;
import com.lupart.technologies.TODO.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final static String USER_NOT_FOUND = "User with username %s not found";
    private final UserRepository userRepository;


    public User getUser(String username) {
        return userRepository.getByUsername(username);
    }


    public User createAppUser(UserDTO userDTO) {
        var user=new User();
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalStateException("This email is already taken!");
        }
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new IllegalStateException("This username is already taken!");
        }
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        BeanUtils.copyProperties(userDTO,user);
       return userRepository.save(user);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, username))
        );
        return new org.springframework.security.core.userdetails.User("admin","$2a$10$mfmPZdFJIxkI8TyXeruoUug7ovxL3.OYMQaBZcXIKQvfTr4A1wDKO",new ArrayList<>());

    }
}
