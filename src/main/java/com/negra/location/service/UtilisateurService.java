package com.negra.location.service;

import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.model.Utilisateur;
import com.negra.location.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.negra.location.utility.ErrorMessage.ERROR_UTILISATEUR_ALREADY_EXISTS;

@Service
@Transactional
public class UtilisateurService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void isUserExists(String username) throws AlreadyExistsException {
        Optional<Utilisateur> user = utilisateurRepository.findByEmail(username);
        user.ifPresent(user1 -> {throw new AlreadyExistsException(ERROR_UTILISATEUR_ALREADY_EXISTS);});
    }
}
