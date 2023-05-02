package com.trantorinc.springbootlocaldevdocker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "resource")
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Name")
  @Getter
  @Setter
  private String name;

  @Column(name = "Description")
  @Getter
  @Setter
  private String description;

  public String toString() {
    return "Resource - Id: " + id + ", Name: " + name;
  }

}
