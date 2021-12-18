package com.transtour.backend.user.repository;

import com.transtour.backend.user.model.Car;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Qualifier("carRepo")
@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
