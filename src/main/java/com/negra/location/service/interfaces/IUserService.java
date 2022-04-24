package com.negra.location.service.interfaces;

import com.negra.location.dto.ClientDto;
import com.negra.location.exception.*;
import com.negra.location.model.User;

public interface IUserService {

    void isUserExists(String username) throws DataNotFoundException, AlreadyExistsException;
    User findByUsername(String username) throws DataNotFoundException, UserNotFoundException;
    String getCurrentUsername() throws CurrentUserNotFoundException;
    User getCurrentUser() throws CurrentUserNotFoundException;
    ClientDto getUserShortInfo(Long idClient) throws DataNotFoundException;
}
