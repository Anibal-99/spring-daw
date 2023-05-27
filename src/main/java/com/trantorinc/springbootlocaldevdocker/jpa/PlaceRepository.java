package com.trantorinc.springbootlocaldevdocker.jpa;

import com.trantorinc.springbootlocaldevdocker.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

}
