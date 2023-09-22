package com.zoubey.api_indicateur.security.services;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zoubey.api_indicateur.Model.Direction;
import com.zoubey.api_indicateur.Model.User;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {
  private static final long serialVersionUID = 1L;

  private Long id;

  private  String nom;

  private String prenom;

  private String nomuser;

  private String email;

  @JsonIgnore
  private String motpass;
  Direction direction;

  private Collection<? extends GrantedAuthority> authorities;

  public UserDetailsImpl(Long id, String nomuser, String email, String prenom, String nom, String motpass,Direction direction,
      Collection<? extends GrantedAuthority> authorities) {
    this.id = id;
    this.nom = nom;
    this.prenom =prenom;
    this.nomuser = nomuser;
    this.email = email;
    this.motpass = motpass;
    this.direction=direction;
    this.authorities = authorities;
  }

  public static UserDetailsImpl build(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream()
        .map(role -> new SimpleGrantedAuthority(role.getNom().name()))
        .collect(Collectors.toList());

    return new UserDetailsImpl(
        user.getId(), 
        user.getNomuser(),
        user.getNom(),
        user.getPrenom(),
        user.getEmail(),
        user.getMotpass(),
        user.getUserDirection(),
        authorities);
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }


  @Override
  public String getPassword() {
    return motpass;
  }

  @Override
  public String getUsername() {
    return nomuser;
  }
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    UserDetailsImpl user = (UserDetailsImpl) o;
    return Objects.equals(id, user.id);
  }
}
