package com.trantorinc.springbootlocaldevdocker.model.views;
import java.util.List;
import com.trantorinc.springbootlocaldevdocker.model.Resource;
import lombok.Data;

@Data
public class PlaceDto {
    private Long id;
    private String name;
    private List<Resource> resources;
}
