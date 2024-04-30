package com.example.proiectITFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername (String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Utilizatorul nu a fost gasit");

        }
        return new CustomUserDetails(user);
    }


    public UserDetails loadUserById(Long id) {
        User user = repo.getUserById(id);
        if(user == null) {
            throw new UsernameNotFoundException("Utilizatorul cu ID-ul dat nu a fost gasit");
        }
        return new CustomUserDetails(user);
    }
}
