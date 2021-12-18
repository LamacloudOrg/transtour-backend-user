package com.transtour.backend.user.controller;

import com.transtour.backend.user.dto.CompanyDTO;
import com.transtour.backend.user.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    CompanyService service;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public CompletableFuture<List<CompanyDTO>> gertAllCompanies() {
        return service.getAllComapies();
    }
}
