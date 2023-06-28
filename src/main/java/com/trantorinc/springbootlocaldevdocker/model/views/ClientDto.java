package com.trantorinc.springbootlocaldevdocker.model.views;

import lombok.Data;

@Data
public class ClientDto {
    private Long id;
    private String name;
    private String surname;
    private Integer dni;
}
