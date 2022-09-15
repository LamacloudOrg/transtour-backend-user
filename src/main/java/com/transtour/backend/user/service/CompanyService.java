package com.transtour.backend.user.service;

import com.github.dozermapper.core.Mapper;
import com.transtour.backend.user.dto.CompanyDTO;
import com.transtour.backend.user.dto.UserDTO;
import com.transtour.backend.user.exception.CompanyNotExists;
import com.transtour.backend.user.exception.UserNotExists;
import com.transtour.backend.user.model.Company;
import com.transtour.backend.user.model.User;
import com.transtour.backend.user.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
                () -> {
                    return repository
                            .findAll()
                            .stream()
                            .map(company -> {
                                CompanyDTO companyDTO = new CompanyDTO();
                                mapper.map(company, companyDTO);
                                return companyDTO;
                            })
                            .collect(Collectors.toList());
                });

        return completableFuture;
    }

    public CompletableFuture<CompanyDTO> getCompanyByName(String fullName) {

                CompletableFuture<CompanyDTO> completableFuture = CompletableFuture.supplyAsync(
                        () -> {
                            Optional<Company> optionalCompany = repository.findByFullName(fullName);
                            optionalCompany.orElseThrow(CompanyNotExists::new);
                            CompanyDTO companyDTO = new CompanyDTO();
                            mapper.map(optionalCompany, companyDTO);
                            return companyDTO;
                        }
                );
        return completableFuture;
    }

}