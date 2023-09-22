package com.zoubey.api_indicateur.payload.request;

import com.zoubey.api_indicateur.Model.Direction;
import com.zoubey.api_indicateur.Model.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;


public class SignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String nomuser;

  @NotBlank
  @Size(min = 3, max = 20)
  private String prenom;

  @NotBlank
  @Size(min = 3, max = 20)
  private String nom;

  @NotBlank
  @Size(max = 50)
  @Email
  private String email;

  private Set<String> role;

  @NotBlank
  @Size(min = 6, max = 40)
  private String motpass;
  private Service userService;
  private Direction userDirection;

  public String getNomuser() {
    return nomuser;
  }

  public void setNomuser(String nomuser) {
    this.nomuser = nomuser;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<String> getRole() {
    return role;
  }

  public void setRole(Set<String> role) {
    this.role = role;
  }

  public String getMotpass() {
    return motpass;
  }

  public void setMotpass(String motpass) {
    this.motpass = motpass;
  }

  public Service getUserService() {
    return userService;
  }

  public void setUserService(Service userService) {
    this.userService = userService;
  }

  public Direction getUserDirection() {
    return userDirection;
  }

  public void setUserDirection(Direction userDirection) {
    this.userDirection = userDirection;
  }
}
