package com.trantorinc.springbootlocaldevdocker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.sql.Date;

import javax.persistence.*;

@Entity
@Table(name = "reservation")
public class Reservation {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "title")
  @Getter
  @Setter
  private String title;

  @Column(name = "reason")
  @Getter
  @Setter
  private String reason;

  @Column(name = "time")
  @Getter
  @Setter
  private Date time;

  @Column(name = "ammount")
  @Getter
  @Setter
  private float ammount;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "state_id")
  @Getter
  @Setter
  private State state;

  public String toString() {
    return "Reservation - Id: " + id + "State " + state;
  }
}
