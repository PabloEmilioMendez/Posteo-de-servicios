package com.tuservicios.tuservicios.repository;

import com.tuservicios.tuservicios.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
