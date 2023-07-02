package com.trantorinc.springbootlocaldevdocker.model.views;
import com.trantorinc.springbootlocaldevdocker.model.Place;
import lombok.Data;
import java.util.*;

@Data
public class ResourceDto {
    private Long id;
    private String name;
    private String description;
    // private List<Place>;
}
