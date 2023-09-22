package com.zoubey.api_indicateur.Controller;


import com.zoubey.api_indicateur.Model.ERole;
import com.zoubey.api_indicateur.Model.Role;
import com.zoubey.api_indicateur.Model.User;
import com.zoubey.api_indicateur.Repositories.RoleRepository;
import com.zoubey.api_indicateur.Repositories.UserRepository;
import com.zoubey.api_indicateur.payload.request.LoginRequest;
import com.zoubey.api_indicateur.payload.request.SignupRequest;
import com.zoubey.api_indicateur.payload.response.JwtResponse;
import com.zoubey.api_indicateur.payload.response.MessageResponse;
import com.zoubey.api_indicateur.security.jwt.JwtUtils;
import com.zoubey.api_indicateur.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getNomuser(), loginRequest.getMotpass()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(
                             userDetails.getId(),
                             userDetails.getEmail(),
                             userDetails.getNom(),
                             userDetails.getNomuser(),
                             userDetails.getPrenom(),
            roles,
            jwt,
            userDetails.getDirection()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    if (userRepository.existsByNomuser(signUpRequest.getNomuser())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) { 
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User();
              user.setNomuser( signUpRequest.getNomuser());
              user.setEmail( signUpRequest.getEmail());
               user.setPrenom(signUpRequest.getPrenom());
               user.setNom(signUpRequest.getNom());
               user.setMotpass(encoder.encode(signUpRequest.getMotpass()));
               user.setUserDirection(signUpRequest.getUserDirection());
               user.setUserService(signUpRequest.getUserService());

    Set<String> strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

    if (strRoles == null) {
      Role userRole = roleRepository.findByNom(ERole.ROLE_USER)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(userRole);
    } else {
      strRoles.forEach(role -> {
        switch (role) {
        case "admin":
          Role adminRole = roleRepository.findByNom(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "mod":
          Role modRole = roleRepository.findByNom(ERole.ROLE_MODERATOR)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(modRole);
          break;

          case "manager":
            Role managerRole = roleRepository.findByNom(ERole.ROLE_MANAGER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(managerRole);
            break;

          case "pilote":
            Role piloteRole = roleRepository.findByNom(ERole.ROLE_PILOTE_PROCESSUS)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(piloteRole);
            break;

          case "sousPilote":
            Role sousPiloteRole = roleRepository.findByNom(ERole.ROLE_SOUS_PILOTE_PROCESSUS)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(sousPiloteRole);
            break;
        default:
          Role userRole = roleRepository.findByNom(ERole.ROLE_USER)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      });
    }

    user.setRoles((roles));
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
