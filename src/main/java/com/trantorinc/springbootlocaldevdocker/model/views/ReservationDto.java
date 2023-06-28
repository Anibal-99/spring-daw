package com.trantorinc.springbootlocaldevdocker.model.views;
import java.sql.Date;

import com.trantorinc.springbootlocaldevdocker.model.Client;
import com.trantorinc.springbootlocaldevdocker.model.State;

import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private String title;
    private Date date;
    private float ammount;
    private State state;
    private Client client;
    
}
