package com.zoubey.api_indicateur.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_service")
    private String serviceName;

    @ManyToOne
    @JoinColumn(name = "direction_service_id")
    private Direction DirectionService;
}
