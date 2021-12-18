package com.transtour.backend.user.controller;

import com.transtour.backend.user.dto.DriverDTO;
import com.transtour.backend.user.dto.RegisterDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public CompletableFuture<String> login(@RequestBody RegisterDTO user) {
        return service.generateToken(user);
    }

    @PostMapping("/oauth/refresh")
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public String refreshToken(@RequestBody UserDTO user) {
        return "por implementar";
    }


    @GetMapping
    @RolesAllowed({"ROLE_ADMIN"})
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<UserDTO> findUser(@RequestParam("dni") Long dni) {
        return service.find(dni);
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
    public CompletableFuture<String> userRegister(@RequestBody RegisterDTO user) {
        return service.register(user);
    }

    @GetMapping("/find/drivers")
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<DriverDTO>> gertAllDrivers(@QueryParam("userType") String userType) {
        return service.getAllDrivers("DRIVER");
    }


}
