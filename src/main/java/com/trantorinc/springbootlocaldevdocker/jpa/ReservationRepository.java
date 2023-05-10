package com.trantorinc.springbootlocaldevdocker.jpa;

import com.trantorinc.springbootlocaldevdocker.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
