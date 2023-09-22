package com.zoubey.api_indicateur.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "calcul_tb")
@AllArgsConstructor
@NoArgsConstructor
public class Calcul{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String  periode;
   String  resultatObtenu;
   String  responsableAnalyse;
    String responsableCalcul;
   String  cause;
   String  analyseCause;
   String  typeAction;
   String  etat;
    String genereIdeeAmelioration;
    String identifiantIdeeAmelioration;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy")
//    private Date anneeCalcul;

    private int anneeCalcul;



    @ManyToOne
            @JoinColumn(name = "user_id")
    User userId;

    @OneToMany(mappedBy = "idCalcul")
    List<PlanAction> planAction;


    @ManyToOne
            @JoinColumn(name = "idIndicateur")
    Indicateur indicateur;

    


    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @JsonManagedReference
    public List<PlanAction> getPlanAction() {
        return planAction;
    }

    public void setPlanAction(List<PlanAction> planAction) {
        this.planAction = planAction;
    }

    @JsonBackReference
    public Indicateur getIndicateur() {
        return indicateur;
    }

    public void setIndicateur(Indicateur indicateur) {
        this.indicateur = indicateur;
    }
}
