package com.negra.location.mapper;

import com.negra.location.dto.CarStickerDueDateDto;
import com.negra.location.model.CarSticker;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarStickerDueDateDtoMapper {

    CarStickerDueDateDto carStickerToCarStickerDueDateDto(CarSticker carSticker);

}
