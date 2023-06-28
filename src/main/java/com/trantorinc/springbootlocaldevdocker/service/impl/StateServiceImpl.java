package com.trantorinc.springbootlocaldevdocker.service.impl;

import com.trantorinc.springbootlocaldevdocker.jpa.StateRepository;
import com.trantorinc.springbootlocaldevdocker.model.State;
import com.trantorinc.springbootlocaldevdocker.model.views.StateDto;
import com.trantorinc.springbootlocaldevdocker.service.StateService;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StateServiceImpl implements StateService {
    
    @Autowired
    private StateRepository stateRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "State not found - id:";
 
    @Override
    public StateDto createState(StateDto stateDto) {
        State state = modelMapper.map(stateDto, State.class);
        stateRepository.save(state);
        stateDto = modelMapper.map(state, StateDto.class);
        log.info(String.format("State %s created successfully", state.getName()));
        return stateDto;
    }

    @Override
    public List<StateDto> findAllStates() {
        List<State> states = stateRepository.findAll();
        return states.stream()
                .map(state -> modelMapper.map(state, StateDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteState(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        stateRepository.delete(state);
        log.info("State deleted successfully");
    }

    @Override
    public StateDto getStateById(Long id) {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(state, StateDto.class);
    }

    @Override
    public void updateState(Long id, StateDto stateDto) {
        State stateToUpdate = modelMapper.map(stateDto, State.class);
        stateToUpdate.setId(id);
        stateRepository.save(stateToUpdate);
        log.info(String.format("State %s updated successfully", stateToUpdate.getName()));
    }
}
