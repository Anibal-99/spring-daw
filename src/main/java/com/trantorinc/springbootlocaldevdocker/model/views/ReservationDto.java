package com.trantorinc.springbootlocaldevdocker.model.views;
import java.sql.Date;

import com.trantorinc.springbootlocaldevdocker.model.views.ClientDto;
import com.trantorinc.springbootlocaldevdocker.model.views.StateDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private Long id;
    private String title;
    private Date date;
    private float ammount;
    private String reason;
    private ClientDto client;
    private StateDto state;

}
