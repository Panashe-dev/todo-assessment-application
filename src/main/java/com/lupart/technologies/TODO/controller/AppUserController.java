package com.lupart.technologies.TODO.controller;

import com.lupart.technologies.TODO.dto.TodoDTO;
import com.lupart.technologies.TODO.dto.UserDTO;
import com.lupart.technologies.TODO.entity.Todo;
import com.lupart.technologies.TODO.entity.User;
import com.lupart.technologies.TODO.service.TodoService;
import com.lupart.technologies.TODO.service.impl.UserService;
import com.lupart.technologies.TODO.utils.Response;
import com.lupart.technologies.TODO.utils.ResponseBuild;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "user")
@Api(tags = {"User Controller"})
@Slf4j
@RequiredArgsConstructor
public class AppUserController {

    private final UserService userService;
    private final ResponseBuild<User> userResponseBuild;
    private final ResponseBuild<Boolean>booleanResponseBuild;


    @PostMapping
    @ApiOperation(notes = "Process  creating a user",
            value = "Process creating a user", nickname = "USER")
    public ResponseEntity<Response> newUSer(@RequestBody @Valid UserDTO userDTO) {
        System.out.println("am in");
        log.info("Create Todo Request");
        return new ResponseEntity<>(userResponseBuild.successResponse
                .apply(userService.createAppUser(userDTO),null), HttpStatus.CREATED);
    }


}
