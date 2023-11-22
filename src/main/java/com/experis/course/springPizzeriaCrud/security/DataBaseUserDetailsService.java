package com.experis.course.springPizzeriaCrud.security;

import com.experis.course.springPizzeriaCrud.model.User;
import com.experis.course.springPizzeriaCrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DataBaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // prendo lo username che viene dalla login e cerco su db uno user con quella email
        Optional<User> loggerUser = userRepository.findByEmail(username);
        if (loggerUser.isPresent()) {
            // c'è un username con quella email
            // restituisco un DataBaseUserDetails con i dati dello User
            return new DataBaseUserDetails(loggerUser.get());
        } else {
            // non c'è un username con quella email
            throw new UsernameNotFoundException(username);
        }
    }
}











