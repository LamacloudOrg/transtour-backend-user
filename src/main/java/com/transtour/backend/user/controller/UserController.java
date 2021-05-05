package com.transtour.backend.user.controller;

import com.transtour.backend.user.dto.UserAccountDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/oauth/token")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> login(UserDTO user){
        return service.generateToken(user);
    }

    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<UserAccountDTO> findUser(String userName){
        return service.find(userName);
    }
}
