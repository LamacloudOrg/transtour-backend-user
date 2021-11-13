package com.transtour.backend.user.service;

import com.github.dozermapper.core.Mapper;
import com.transtour.backend.user.dto.CompanyDTO;
import com.transtour.backend.user.dto.DriverDTO;
import com.transtour.backend.user.repository.CompanyRepository;
import com.transtour.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    @Autowired
    @Qualifier("companyRepo")
    CompanyRepository repository;

    @Autowired
    private Mapper mapper;


    public CompletableFuture<List<CompanyDTO>> getAllComapies() {
        CompletableFuture<List<CompanyDTO>> completableFuture = CompletableFuture.supplyAsync(
                ()->{
                    return repository
                            .findAll()
                            .stream()
                            .map( company -> {
                                CompanyDTO companyDTO = new CompanyDTO();
                                mapper.map(company,companyDTO);
                                return companyDTO;
                            })
                            .collect(Collectors.toList());
                });

        return completableFuture;
    }
}
