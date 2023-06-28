package com.trantorinc.springbootlocaldevdocker.service;

import java.util.List;

import com.trantorinc.springbootlocaldevdocker.model.views.StateDto;

public interface StateService {
    StateDto createState(StateDto stateDto);
    List<StateDto> findAllStates();
    void deleteState(Long id);
    StateDto getStateById(Long id);
    void updateState(Long id, StateDto stateDto); 
    
}
