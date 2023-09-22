package com.zoubey.api_indicateur.Repositories;

import com.zoubey.api_indicateur.Model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}