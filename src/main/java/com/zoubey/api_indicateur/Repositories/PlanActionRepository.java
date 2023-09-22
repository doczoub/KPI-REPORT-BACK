package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.PlanAction;
import com.zoubey.api_indicateur.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanActionRepository extends JpaRepository<PlanAction, Long> {
    List<PlanAction> findByUserId_Id(Long id);

    List<PlanAction> findByIdCalcul_Id(Long id);


}