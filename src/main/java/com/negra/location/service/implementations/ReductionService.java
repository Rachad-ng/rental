package com.negra.location.service.implementations;

import com.negra.location.exception.DataNotFoundException;
import com.negra.location.model.Reduction;
import com.negra.location.repository.ReductionRepository;
import com.negra.location.service.interfaces.IReductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ReductionService implements IReductionService {

    @Autowired
    private ReductionRepository reductionRepository;

    @Override
    public void createReduction(Reduction reduction){
        reductionRepository.save(reduction);
    }

    @Override
    public List<Reduction> findAll() throws DataNotFoundException {
        return reductionRepository.findAll();
    }

}
