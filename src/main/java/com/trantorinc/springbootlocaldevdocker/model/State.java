package com.trantorinc.springbootlocaldevdocker.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "state")
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  @Getter
  @Setter
  private String name;

  @Column(name = "description")
  @Getter
  @Setter
  private String description;

  public String toString() {
    return "State - Id: " + id + ", Name: " + name;
  }
}
