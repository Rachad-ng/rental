package com.negra.location.mapper;

import com.negra.location.dto.CategoryDto;
import com.negra.location.model.Category;
import org.mapstruct.Mapper;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryDtoMapper {

    CategoryDto categoryToCategoryDto(Category category);

    List<CategoryDto> categoryToCategoryDto(Collection<Category> categories);

}
