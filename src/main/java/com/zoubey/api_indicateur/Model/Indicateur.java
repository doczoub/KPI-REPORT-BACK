package com.zoubey.api_indicateur.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Indicateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    private String NomMesure;
    @NotNull
    private String CodeMesure;
    @NotNull
    private String Norme;
    @NotNull
    private String TypeNorme;
    private String periodicite;
    private String nomProcesus;
    private String typeProcessus;
    private String objectifQualite;
    private String objectifMesure;
    private String alertRouge;
    private String alertOrange;
    private String alertVert;

    @ManyToOne
    @JoinColumn(name = "idUser")
    User user;

    @OneToMany(mappedBy = "indicateur")
    List<Calcul> calculs;

    @ManyToOne
            @JoinColumn(name = "idDirection")
    Direction direction;

    @JsonManagedReference
    public List<Calcul> getCalculs() {
        return calculs;
    }

    public void setCalculs(List<Calcul> calculs) {
        this.calculs = calculs;
    }
}
