package com.negra.location.mapper;

import com.negra.location.dto.MarkWithModelDto;
import com.negra.location.dto.ModelDto;
import com.negra.location.model.Mark;
import com.negra.location.model.Model;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MarkWithModelDtoMapper {

    @Autowired
    protected ModelDtoMapper modelDtoMapper;

    public abstract MarkWithModelDto markToMarkWithModelDto(Mark mark);
    public abstract List<MarkWithModelDto> markToMarkWithModelDto(Collection<Mark> marks);

    @BeforeMapping
    protected void loadOrdredModelDtos(Mark mark, @MappingTarget MarkWithModelDto markWithModelDto){
        List<Model> modelList = new ArrayList<>(mark.getModelSet()).stream().sorted(Comparator.comparing(Model::getLibelle)).collect(Collectors.toList());
        markWithModelDto.setModelDtos(modelDtoMapper.modelToModelDto(modelList));
    }

}
