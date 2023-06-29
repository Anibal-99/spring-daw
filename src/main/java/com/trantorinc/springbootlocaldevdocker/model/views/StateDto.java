package com.trantorinc.springbootlocaldevdocker.model.views;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StateDto {
    private Long id;
    private String name;
    private String description;
}
