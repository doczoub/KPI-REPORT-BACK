package com.zoubey.api_indicateur.Model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Enumerated(EnumType.STRING)
        @Column(length = 20)
        private ERole nom;

        public Role() {
        }

        public Role(ERole nom) {
                this.nom = nom;
        }

        public Integer getId() {
                return id;
        }

        public void setId(Integer id) {
                this.id = id;
        }

        public void setNom(ERole nom) {
                this.nom = nom;
        }

        public ERole getNom() {
                return nom;
        }
}
