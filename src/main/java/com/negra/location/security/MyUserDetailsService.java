package com.negra.location.security;

import com.negra.location.model.Utilisateur;
import com.negra.location.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.negra.location.utility.ErrorMessage.ERROR_UTILISATEUR_NOT_FOUND;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Utilisateur> user = utilisateurRepository.findByEmail(username);

        user.orElseThrow(() -> new UsernameNotFoundException(ERROR_UTILISATEUR_NOT_FOUND));

        return user.map(MyUserDetails::new).get();
    }

}
