package com.trantorinc.springbootlocaldevdocker.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trantorinc.springbootlocaldevdocker.jpa.ReservationRepository;
import com.trantorinc.springbootlocaldevdocker.model.Reservation;
import com.trantorinc.springbootlocaldevdocker.model.views.ReservationDto;
import com.trantorinc.springbootlocaldevdocker.service.ReservationService;


@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;
    private final ModelMapper modelMapper = new ModelMapper();
    private static final String ID_NOT_FOUND = "Reservation not found - id:";
 

    @Override
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Reservation reservation = modelMapper.map(reservationDto, Reservation.class);
        reservationRepository.save(reservation);
        reservationDto = modelMapper.map(reservation, ReservationDto.class);
        log.info(String.format("Reservation %s created successfully", reservation.getTitle()));
        return reservationDto;
    }

    @Override
    public List<ReservationDto> findAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservations.stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteReservation(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        reservationRepository.delete(reservation);
        log.info("Reservation deleted successfully");
    }

    @Override
    public ReservationDto getReservationById(Long id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(ID_NOT_FOUND + id, new EntityNotFoundException(ID_NOT_FOUND + id));
                    throw new EntityNotFoundException(ID_NOT_FOUND + id);
                });
        return modelMapper.map(reservation, ReservationDto.class);
    }

    @Override
    public void updateReservation(Long id, ReservationDto reservationDto) {
        Reservation reservationToUpdate = modelMapper.map(reservationDto, Reservation.class);
        reservationToUpdate.setId(id);
        reservationRepository.save(reservationToUpdate);
        log.info(String.format("Reservation %s updated successfully", reservationToUpdate.getTitle()));
    }

    public void deleteReservationByIdClient(Long id) {
        this.findAllReservations().stream().filter(r-> r.getClient().getId().equals(id)).forEach(r -> reservationRepository.delete(modelMapper.map(r, Reservation.class)));
    }
}
