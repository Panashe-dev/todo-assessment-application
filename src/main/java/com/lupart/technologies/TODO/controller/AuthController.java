package com.lupart.technologies.TODO.controller;

import com.lupart.technologies.TODO.auth.JWTRequest;
import com.lupart.technologies.TODO.auth.JWTResponse;
import com.lupart.technologies.TODO.auth.JWTUtility;
import com.lupart.technologies.TODO.service.impl.UserService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "auth")
@Api(tags = {"Auth Controller"})
@Slf4j
@RequiredArgsConstructor
public class AuthController {


    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUsername(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            throw  new Exception("INVALID CREDENTIALS", e);
        }
        final UserDetails userDetails=
                userService.loadUserByUsername(jwtRequest.getUsername());
        final String token=
                jwtUtility.generateToken(userDetails);
        return new JWTResponse(token);

    }
}
