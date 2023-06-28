package com.trantorinc.springbootlocaldevdocker.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Description")
  private String description;
}
