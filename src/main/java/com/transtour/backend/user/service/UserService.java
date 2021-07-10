package com.transtour.backend.user.service;

import com.github.dozermapper.core.Mapper;
import com.transtour.backend.user.dto.UserAccountDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.exception.InactiveUser;
import com.transtour.backend.user.exception.UserNotExists;
import com.transtour.backend.user.model.User;
import com.transtour.backend.user.repository.UserRepository;
import com.transtour.backend.user.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
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

    public CompletableFuture<String> generateToken(UserDTO userDTO){

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> optionalUser = repository.findByUserNameAndPassword(userDTO.getUserName(), userDTO.getPassword());

                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    if(!user.isEnabled()) throw new InactiveUser();
                    return TokenUtil.createJWT("1",user.getUserName(),"generacion token",2000L);
                }
        );

        return completableFuture;
    }

    public CompletableFuture<UserAccountDTO> find(String userName){

        CompletableFuture<UserAccountDTO> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> optionalUser = repository.findByUserName(userName);
                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                    UserAccountDTO userAccountDTO = new UserAccountDTO();
                    mapper.map(user,userAccountDTO);
                    return userAccountDTO;
                }
        );

        return completableFuture;
    }

    public CompletableFuture<List<UserAccountDTO>> findDrivers(String role,Pageable pageable){

        CompletableFuture<List<UserAccountDTO>> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                   List<User> users = repository.findByRolesIn(Arrays.asList(role),pageable);
                    return users.stream().map(user -> {
                        UserAccountDTO userAccountDTO = new UserAccountDTO();
                        mapper.map(user,userAccountDTO);
                        return userAccountDTO;
                    }).collect(Collectors.toList());

                }
        );

        return completableFuture;
    }

    public CompletableFuture<String> updatePassword (UserDTO userDTO){

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    Optional<User> optionalUser = repository.findByUserName(userDTO.getUserName());

                    optionalUser.orElseThrow(UserNotExists::new);
                    User user = optionalUser.get();
                //  if(!user.isEnabled()) throw new InactiveUser();
                    user.setPassword(userDTO.getPassword());
                    user.setEnabled(true);
                    repository.save(user);
                    return "Password Updated";
                }
        );

        return completableFuture;
    }
}
