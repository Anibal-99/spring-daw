package com.trantorinc.springbootlocaldevdocker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  private String title;

  @Column(name = "reason")
  private String reason;

  @Column(name = "time")
  private Date date;

  @Column(name = "ammount")
  private float ammount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "state_id")
  private State state;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id")
  private Client client;
}
