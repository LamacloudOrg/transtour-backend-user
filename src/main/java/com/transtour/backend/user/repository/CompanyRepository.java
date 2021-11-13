package com.transtour.backend.user.repository;

import com.transtour.backend.user.model.Company;
import com.transtour.backend.user.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("companyRepo")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
