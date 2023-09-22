package com.zoubey.api_indicateur.security.services;


import com.zoubey.api_indicateur.Model.User;
import com.zoubey.api_indicateur.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  UserRepository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String nomuser) throws UsernameNotFoundException {
    User user = userRepository.findByNomuser(nomuser)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + nomuser));

    return UserDetailsImpl.build(user);
  }

}
