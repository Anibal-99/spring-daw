package com.trantorinc.springbootlocaldevdocker.service;
import com.trantorinc.springbootlocaldevdocker.model.views.ResourceDto;
import java.util.List;

public interface ResourceService {
    ResourceDto createResource(ResourceDto resourceDto);
    List<ResourceDto> findAllResources();
    void deleteResource(Long id);
    ResourceDto getResourceById(Long id);
    void updateResource(Long id, ResourceDto resourceDto); 
}
