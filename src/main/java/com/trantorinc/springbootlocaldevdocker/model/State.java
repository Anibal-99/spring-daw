package com.trantorinc.springbootlocaldevdocker.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "state")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;
}
