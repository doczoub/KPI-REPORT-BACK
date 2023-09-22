package com.zoubey.api_indicateur.Model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user_tb",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "nomuser"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private  Long id;
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(name = "email")
    private String email;
    @NotBlank
    @Size(max = 50)
    @Column(name = "nom")
    private String nom;
    @NotBlank
    @Size(max = 20)
    @Column(name = "nomuser")
   private  String nomuser;
    @NotBlank
    @Size(max = 50)
    @Column(name = "prenom")
   private  String prenom;
    @NotBlank
    @Size(max = 120)
    @Column(name = "motpass")
   private  String motpass;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "user_service_id")
    private Service userService;

    @ManyToOne
    @JoinColumn(name = "user_direction_id")
    private Direction userDirection;

    public Direction getUserDirection() {
        return userDirection;
    }

    public void setUserDirection(Direction userDirection) {
        this.userDirection = userDirection;
    }

    public Service getUserService() {
        return userService;
    }

    public void setUserService(Service userService) {
        this.userService = userService;
    }

    public  User(){
    }

    public User( String email, String prenom, String nom,String nomuser, String motpass) {
        this.email = email;
        this.prenom = prenom;
        this.nom = nom;
        this.nomuser = nomuser;
        this.motpass = motpass;
    }
    public String getNomuser() {
        return nomuser;
    }

    public void setNomuser(String nomuser) {
        this.nomuser = nomuser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotpass() {
        return motpass;
    }

    public void setMotpass(String motpass) {
        this.motpass = motpass;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
