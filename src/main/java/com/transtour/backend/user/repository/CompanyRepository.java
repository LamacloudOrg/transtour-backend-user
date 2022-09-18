package com.transtour.backend.user.repository;

import com.transtour.backend.user.model.Company;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Qualifier("companyRepo")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByFullName(String fullName);

}
