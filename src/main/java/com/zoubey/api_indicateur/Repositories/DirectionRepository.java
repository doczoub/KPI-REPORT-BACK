package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction, Long> {
}