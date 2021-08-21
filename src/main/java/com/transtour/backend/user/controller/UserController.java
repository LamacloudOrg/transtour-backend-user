package com.transtour.backend.user.controller;

import com.transtour.backend.user.dto.RegisterDTO;
import com.transtour.backend.user.dto.UserAccountDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.model.User;
import com.transtour.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService service;

    @PostMapping("/oauth/token")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> login(@RequestBody  UserDTO user){
        return service.generateToken(user);
    }

    @PostMapping("/oauth/refresh")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String refreshToken(@RequestBody  UserDTO user){
        return "por implementar";
    }


    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<UserAccountDTO> findUser(@RequestParam("userName") String userName){
        return service.find(userName);
    }

    /*
    @GetMapping("drivers")
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<UserAccountDTO>> findUserDrivers(Pageable pageable){
        String role = "ROLE_DRIVER";
        return service.findDrivers(role,pageable);
    }
    */

    @PostMapping("/update/password")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<String> userRegister(@RequestBody RegisterDTO user){
        return service.register(user);
    }

    @GetMapping("/find/usersByType")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<UserDTO>> userByType (@QueryParam("userType") String userType){
        return service.userByType(userType);
    }

}
