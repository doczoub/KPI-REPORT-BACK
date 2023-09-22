package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.Suivi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuiviRepository extends JpaRepository<Suivi, Long> {
    List<Suivi> findByUserId_Id(Long id);

    List<Suivi> findByIdCalcul_Id(Long id);





}