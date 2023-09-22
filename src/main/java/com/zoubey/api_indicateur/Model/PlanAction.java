package com.zoubey.api_indicateur.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanAction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String action;
    private Date dateLivraisonPlanAction;
    private String responsableAction;
    private String etat;
    @ElementCollection
    @CollectionTable(name = "plan_action_suivi", joinColumns = @JoinColumn(name = "plan_action_id"))
    @Column(name = "suivi")
    private List<String> suivi;

    @ManyToOne
    @JoinColumn(name = "calcul_id")
    Calcul idCalcul;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;

    @JsonBackReference
    public Calcul getIdCalcul() {
        return idCalcul;
    }

    public void setIdCalcul(Calcul idCalcul) {
        this.idCalcul = idCalcul;
    }
}
