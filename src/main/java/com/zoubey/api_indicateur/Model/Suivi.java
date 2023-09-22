package com.zoubey.api_indicateur.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Suivi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String action;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateAction;
    private String  efficacite;
    private String justificationEfficacite;

    @ManyToOne
    @JoinColumn(name = "calcul_id")
    Calcul idCalcul;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;


    @ManyToOne
    @JoinColumn(name = "plan_action_id")
    PlanAction planAction;
}
