package com.transtour.backend.user.service;

import com.github.dozermapper.core.Mapper;
import com.transtour.backend.user.dto.CarDTO;
import com.transtour.backend.user.dto.DriverDTO;
import com.transtour.backend.user.dto.RegisterDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.exception.InactiveUser;
import com.transtour.backend.user.exception.UserNotExists;
import com.transtour.backend.user.model.User;
import com.transtour.backend.user.repository.UserRepository;
import com.transtour.backend.user.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    @Qualifier("userRepo")
    UserRepository repository;

    @Autowired
    private Mapper mapper;

    public CompletableFuture<String> generateToken(RegisterDTO userDTO){

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> optionalUser = repository.findByDniAndPassword(userDTO.getDni(), userDTO.getPassword());

                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    if(!user.isEnabled()) throw new InactiveUser();
                    return TokenUtil.createJWT("1",null,user.getDni().toString(), user.getRole(),2000L);
                }
        );

        return completableFuture;
    }

    public CompletableFuture<UserDTO> find(Long dni){

        CompletableFuture<UserDTO> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> optionalUser = repository.findByDni(dni);
                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    UserDTO userAccountDTO = new UserDTO();
                    mapper.map(user,userAccountDTO);
                    return userAccountDTO;
                }
        );

        return completableFuture;
    }



    public CompletableFuture<String> register(RegisterDTO registerDTO) {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> userOpt = repository.findByDni(registerDTO.getDni());
                    userOpt.orElseThrow(UserNotExists::new);
                    User user = userOpt.get();
                    user.setEnabled(true);
                    user.setPassword(registerDTO.getPassword());
                    repository.save(user);
                    return "se dio de alta";

                }
        );

        return completableFuture;
    }


    public CompletableFuture<List<DriverDTO>> getAllDrivers(String role) {
        CompletableFuture<List<DriverDTO>> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                return repository
                .findByRole(role)
                .stream()
                .filter( user -> user.isEnabled())
                .map(user -> {
                    DriverDTO driverDTO = new DriverDTO();
                    CarDTO carDTO = new CarDTO();
                    mapper.map(user,driverDTO);
                    if (user.getCar() != null)  mapper.map(user.getCar(),carDTO);
                    else   driverDTO.setCar(carDTO);
                    return driverDTO;
                })
                .collect(Collectors.toList());
                });

        return completableFuture;
    }

}
