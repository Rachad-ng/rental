package com.negra.location.service.implementations;

import com.negra.location.dto.HomeSearchCarDto;
import com.negra.location.dto.MarkWithModelDto;
import com.negra.location.model.Mark;
import com.negra.location.service.interfaces.IAddressService;
import com.negra.location.service.interfaces.IMarkService;
import com.negra.location.service.interfaces.IModelService;
import com.negra.location.service.interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SearchService implements ISearchService {

    @Autowired
    private IMarkService markService;
    @Autowired
    private IAddressService addressService;

    @Override
    public HomeSearchCarDto initializeSearchCarForm() {

        // Get Marks With Models
        List<Mark> markList = markService.findAll();
        List<MarkWithModelDto> markWithModelDtos = new ArrayList<>();
        MapperService.marksToMarkWithModelDtos(markList, markWithModelDtos);

        // Get Available Towns
        List<String> townList = addressService.findAvailableTowns();

        HomeSearchCarDto homeSearchCarDto = new HomeSearchCarDto();
        homeSearchCarDto.setMarkWithModelDtos(markWithModelDtos);
        homeSearchCarDto.setTowns(townList);

        return homeSearchCarDto;
    }

}
