package com.anffercastillo.heroes.services;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anffercastillo.heroes.repositories.UserRepository;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

  private UserRepository userRepository;

  public CustomAuthenticationProvider(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    var username = authentication.getName();
    var password = authentication.getCredentials().toString();

    var user =
        userRepository
            .findUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("WRONG USERNAME"));

    var authorities =
        Arrays.asList(user.getRoles().split(",")).stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(username, password, authorities);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}
