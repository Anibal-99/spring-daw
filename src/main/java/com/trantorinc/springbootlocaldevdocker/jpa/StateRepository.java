package com.trantorinc.springbootlocaldevdocker.jpa;

import com.trantorinc.springbootlocaldevdocker.model.State;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {

}
