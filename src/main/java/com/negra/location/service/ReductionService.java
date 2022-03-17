package com.negra.location.service;

import com.negra.location.model.Reduction;
import com.negra.location.repository.ReductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ReductionService {

    @Autowired
    private ReductionRepository reductionRepository;

    public void createReduction(Reduction reduction){
        reductionRepository.save(reduction);
    }

}
