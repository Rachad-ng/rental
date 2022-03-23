package com.negra.location.service.interfaces;

import com.negra.location.exception.AlreadyExistsException;
import com.negra.location.exception.CurrentUserNotFoundException;
import com.negra.location.exception.UserNotFoundException;
import com.negra.location.model.User;

public interface IUserService {

    void isUserExists(String username) throws AlreadyExistsException;
    User findByUsername(String username) throws UserNotFoundException;
    String getCurrentUsername() throws CurrentUserNotFoundException;

}
