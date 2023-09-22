package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.Indicateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndicateurRepository extends JpaRepository<Indicateur, Long> {
    List<Indicateur> findByDirection_Id(Long id);


    List<Indicateur> findByCalculs_Id(Long id);


}