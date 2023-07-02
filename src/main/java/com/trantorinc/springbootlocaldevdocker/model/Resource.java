package com.trantorinc.springbootlocaldevdocker.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "resource")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Resource {
  @Id
  @GeneratedValue
  private long id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Description")
  private String description;

  @JsonIgnore
  @ManyToMany(mappedBy = "resources", fetch = FetchType.LAZY)
  @JsonBackReference
  private List<Place> places;
}
