package com.negra.location.mapper;

import com.negra.location.dto.CarStickerDto;
import com.negra.location.model.CarSticker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarStickerDtoMapper {

    CarStickerDto carStickerToCarStickerDto(CarSticker carSticker);

}
