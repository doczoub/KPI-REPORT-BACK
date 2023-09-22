package com.zoubey.api_indicateur.payload.response;

import com.zoubey.api_indicateur.Model.Direction;

import javax.persistence.Column;
import java.util.List;



public class JwtResponse {
  private String token;
  private String type = "Bearer";
  private Long id;
  @Column(name = "nom")
  private String nom;
  @Column(name = "email")
  private String email;
  @Column(name = "nomuser")
  private String nomuser;
  @Column(name = "prenom")
  private  String prenom;

  private List<String> roles;
  Direction direction;

  public JwtResponse() {
  }

  public JwtResponse(Long id, String nom, String email, String nomuser,  String prenom, List<String> roles, String accessToken, Direction direction) {
    this.id = id;
    this.nom = nom;
    this.email = email;
    this.nomuser = nomuser;
    this.prenom = prenom;
    this.roles = roles;
    this.token = accessToken;
    this.direction= direction;
  }

  public String getAccessToken() {
    return token;
  }

  public void setAccessToken(String accessToken) {
    this.token = accessToken;
  }

  public String getTokenType() {
    return type;
  }

  public void setTokenType(String tokenType) {
    this.type = tokenType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNomuser() {
    return nomuser;
  }

  public void set(String nomuser) {
    this.nomuser = nomuser;
  }

  public List<String> getRoles() {
    return roles;
  }
  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

  public String getNom() {
    return nom;
  }

  public void setNom(String nom) {
    this.nom = nom;
  }

  public String getPrenom() {
    return prenom;
  }

  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }

  public Direction getDirection() {
    return direction;
  }

  public void setDirection(Direction direction) {
    this.direction = direction;
  }
}
