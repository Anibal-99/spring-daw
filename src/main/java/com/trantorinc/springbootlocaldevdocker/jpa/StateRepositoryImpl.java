package com.trantorinc.springbootlocaldevdocker.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class StateRepositoryImpl {
  @PersistenceContext
  private EntityManager em;
}
