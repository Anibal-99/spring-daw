package com.trantorinc.springbootlocaldevdocker.service;
import com.trantorinc.springbootlocaldevdocker.model.views.PlaceDto;
import java.util.List;

public interface PlaceService {
    PlaceDto createPlace(PlaceDto placeDto);
    List<PlaceDto> findAllPlaces();
    void deletePlace(Long id);
    PlaceDto getPlaceById(Long id);
    void updatePlace(Long id, PlaceDto placeDto); 
}
