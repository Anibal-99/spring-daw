package com.trantorinc.springbootlocaldevdocker.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantorinc.springbootlocaldevdocker.jpa.PlaceRepository;
import com.trantorinc.springbootlocaldevdocker.jpa.ResourceRepository;
import com.trantorinc.springbootlocaldevdocker.model.Place;
import com.trantorinc.springbootlocaldevdocker.model.Resource;
import com.trantorinc.springbootlocaldevdocker.model.views.PlaceDto;
import com.trantorinc.springbootlocaldevdocker.service.PlaceService;

@Service
@Slf4j
public class PlaceServiceImpl implements PlaceService {
    
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ResourceRepository resourceRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Place not found - id:";
 
    @Override
    public PlaceDto createPlace(PlaceDto placeDto) {
        Place place = modelMapper.map(placeDto, Place.class);
        place.getResources().clear();
        // place.getResources().addAll(

        placeDto.getResources()
            .stream()
            .map(resource -> {
                return resourceRepository.findById(resource.getId()).get();
            }).forEach(resource -> place.addResource(resource));
        
        // System.out.println(place.getResources());

        placeRepository.save(place);
        placeDto = modelMapper.map(place, PlaceDto.class);
        log.info(String.format("Place %s created successfully", place.getName()));
        return placeDto;
    }

    @Override
    public List<PlaceDto> findAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return places.stream()
                .map(place -> modelMapper.map(place, PlaceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePlace(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        placeRepository.delete(place);
        log.info("Place deleted successfully");
    }

    @Override
    public PlaceDto getPlaceById(Long id) {
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(place, PlaceDto.class);
    }

    @Override
    public void updatePlace(Long id, PlaceDto placeDto) {
        Place placeToUpdate = modelMapper.map(placeDto, Place.class);
        placeToUpdate.setId(id);
        placeRepository.save(placeToUpdate);
        log.info(String.format("Place %s updated successfully", placeToUpdate.getName()));
    }
}
