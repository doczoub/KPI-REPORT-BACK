package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.ERole;
import com.zoubey.api_indicateur.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByNom(ERole nom);

    @Override
    boolean existsById(Integer integer);
}