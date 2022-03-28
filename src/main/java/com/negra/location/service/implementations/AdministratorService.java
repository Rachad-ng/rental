package com.negra.location.service.implementations;

import com.negra.location.model.Administrator;
import com.negra.location.repository.AdministrateurRepository;
import com.negra.location.service.interfaces.IAdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdministratorService implements IAdministratorService {

    @Autowired
    private AdministrateurRepository administrateurRepository;
    @Autowired
    private UserService userService;

    public void createAdministrateur(Administrator administrator){
        userService.isUserExists(administrator.getEmail());
        administrateurRepository.save(administrator);
    }

}
