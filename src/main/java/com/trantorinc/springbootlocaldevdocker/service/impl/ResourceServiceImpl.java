package com.trantorinc.springbootlocaldevdocker.service.impl;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantorinc.springbootlocaldevdocker.jpa.ResourceRepository;
import com.trantorinc.springbootlocaldevdocker.model.Resource;
import com.trantorinc.springbootlocaldevdocker.model.views.ResourceDto;
import com.trantorinc.springbootlocaldevdocker.service.ResourceService;


@Service
@Slf4j
public class ResourceServiceImpl implements ResourceService{
    
    @Autowired
    private ResourceRepository resourceRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Resource not found - id:";
 

    @Override
    public ResourceDto createResource(ResourceDto resourceDto) {
        Resource resource = modelMapper.map(resourceDto, Resource.class);
        resourceRepository.save(resource);
        resourceDto = modelMapper.map(resource, ResourceDto.class);
        log.info(String.format("Resource %s created successfully", resource.getName()));
        return resourceDto;
    }

    @Override
    public List<ResourceDto> findAllResources() {
        List<Resource> resources = resourceRepository.findAll();
        return resources.stream()
                .map(resource -> modelMapper.map(resource, ResourceDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteResource(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        resourceRepository.delete(resource);
        log.info("Resource deleted successfully");
    }

    @Override
    public ResourceDto getResourceById(Long id) {
        Resource resource = resourceRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(resource, ResourceDto.class);
    }

    @Override
    public void updateResource(Long id, ResourceDto resourceDto) {
        Resource resourceToUpdate = modelMapper.map(resourceDto, Resource.class);
        resourceToUpdate.setId(id);
        resourceRepository.save(resourceToUpdate);
        log.info(String.format("Resource %s updated successfully", resourceToUpdate.getName()));
    }
}
