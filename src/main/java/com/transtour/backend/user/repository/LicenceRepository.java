package com.transtour.backend.user.repository;

import com.transtour.backend.user.model.Licence;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("licenceRepo")
@Repository
public interface LicenceRepository extends JpaRepository<Licence, Long> {
}
