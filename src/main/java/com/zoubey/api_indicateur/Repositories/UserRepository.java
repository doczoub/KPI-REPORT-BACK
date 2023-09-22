package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByNomuser(String nomuser);

    boolean existsByNomuser(String nomuser);

    boolean existsByEmail(String email);
}