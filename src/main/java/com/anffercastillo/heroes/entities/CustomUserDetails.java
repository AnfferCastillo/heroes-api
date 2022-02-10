package com.anffercastillo.heroes.entities;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

  private String username;

  private String password;
  private List<? extends GrantedAuthority> roles;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRoles(List<? extends GrantedAuthority> roles) {
    this.roles = roles;
  }

  public static CustomUserDetails buildUserDetails(User user) {
    var userDetails = new CustomUserDetails();
    userDetails.setPassword(user.getPassword());
    userDetails.setUsername(user.getUsername());
    var roles =
        Arrays.asList(user.getRoles().split(",")).stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    userDetails.setRoles(roles);

    return userDetails;
  }
}
