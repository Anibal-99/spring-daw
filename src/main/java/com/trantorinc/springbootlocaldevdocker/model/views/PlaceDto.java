package com.trantorinc.springbootlocaldevdocker.model.views;
import com.trantorinc.springbootlocaldevdocker.model.views.ResourceDto;
import lombok.Data;
import java.util.*;

@Data
public class PlaceDto {
    private Long id;
    private String name;
    private List<ResourceDto> resources;
}
