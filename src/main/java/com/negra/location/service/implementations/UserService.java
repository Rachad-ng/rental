package com.negra.location.service.implementations;

import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.CurrentUserNotFoundException;
import com.negra.location.exception.UserNotFoundException;
import com.negra.location.model.User;
import com.negra.location.repository.UserRepository;
import com.negra.location.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

import static com.negra.location.utility.ErrorMessage.*;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    // Methode de verification d'unicité des usernames lors de l'inscription
    public void isUserExists(String username) {
        Optional<User> user = userRepository.findByEmail(username);
        user.ifPresent(user1 -> {throw new AlreadyExistsException(ERROR_USER_ALREADY_EXISTS);});
    }

    public User findByUsername(String username){
        Optional<User> optionalUser = userRepository.findByEmail(username);
        if(optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
    }

    public String getCurrentUsername() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else
            throw new CurrentUserNotFoundException(ERROR_CURRENT_USER_NOT_FOUND);

        return username;
    }
}
