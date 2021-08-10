package com.transtour.backend.user.repository;

import com.transtour.backend.user.model.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
@Qualifier("userRepo")
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByDniAndPassword(Long dni, String password);
    Optional<User> findByDni(Long dni);
    List<User> findByRolesIn(Collection<String> names, Pageable pageable);
}
