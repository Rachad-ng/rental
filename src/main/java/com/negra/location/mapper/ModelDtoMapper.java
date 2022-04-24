package com.negra.location.mapper;

import com.negra.location.dto.ModelDto;
import com.negra.location.model.Model;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ModelDtoMapper {

    ModelDto modelToModelDto(Model model);
    List<ModelDto> modelToModelDto(Collection<Model> models);

}
