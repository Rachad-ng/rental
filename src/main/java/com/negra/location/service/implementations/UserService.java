package com.negra.location.service.implementations;

import com.negra.location.dto.ClientDto;
import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.CurrentUserNotFoundException;
import com.negra.location.exception.DataNotFoundException;
import com.negra.location.exception.UserNotFoundException;
import com.negra.location.mapper.ClientDtoMapper;
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
    @Autowired
    private ClientDtoMapper clientDtoMapper;

    // Methode de verification d'unicité des usernames lors de l'inscription
    @Override
    public void isUserExists(String username) throws DataNotFoundException, AlreadyExistsException {
        Optional<User> optionalUser;
        try {
            optionalUser = userRepository.findByEmail(username);
        }catch(Exception e){
            throw new DataNotFoundException(ERROR_DATA);
        }

        optionalUser.ifPresent(user -> {throw new AlreadyExistsException(ERROR_USER_ALREADY_EXISTS);});
    }

    @Override
    public User findByUsername(String username) throws DataNotFoundException, UserNotFoundException{

        Optional<User> optionalUser;
        try {
            optionalUser = userRepository.findByEmail(username);
        }catch(Exception e){
            throw new DataNotFoundException(ERROR_DATA);
        }

        if(optionalUser.isPresent())
            return optionalUser.get();
        else
            throw new UserNotFoundException(ERROR_USER_NOT_FOUND);
    }

    @Override
    public String getCurrentUsername() throws CurrentUserNotFoundException {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            UserDetails userDetails = (UserDetails) principal;
            username = userDetails.getUsername();
        }else
            throw new CurrentUserNotFoundException(ERROR_CURRENT_USER_NOT_FOUND);

        return username;
    }

    @Override
    public User getCurrentUser() throws CurrentUserNotFoundException {
        try{
            return findByUsername(getCurrentUsername());
        }catch (DataNotFoundException | UserNotFoundException e){
            throw new CurrentUserNotFoundException(ERROR_CURRENT_USER_NOT_FOUND);
        }
    }

    @Override
    public ClientDto getUserShortInfo(Long idClient) throws DataNotFoundException {
        Optional<User> optionalUser = userRepository.findById(idClient);
        if(optionalUser.isPresent()){
            return clientDtoMapper.userToClientDto(optionalUser.get());
        }
        else
            throw new DataNotFoundException(ERROR_CLIENT_NOT_FOUND);
    }
}
