package com.trantorinc.springbootlocaldevdocker.service;
import com.trantorinc.springbootlocaldevdocker.model.views.ReservationDto;
import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDto);
    List<ReservationDto> findAllReservations();
    void deleteReservation(Long id);
    ReservationDto getReservationById(Long id);
    void updateReservation(Long id, ReservationDto reservationDto); 
}
