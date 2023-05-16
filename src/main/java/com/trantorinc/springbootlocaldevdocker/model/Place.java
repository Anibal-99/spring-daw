package com.trantorinc.springbootlocaldevdocker.model;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;

import javax.persistence.*;

import java.util.Set;
import java.util.HashSet;


@Entity
@Table(name = "place")
public class Place {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
        name = "place_resource",
        joinColumns = {@JoinColumn(name = "place_id")},
        inverseJoinColumns = {@JoinColumn(name="resource_id")}
    )
    private Set<Resource> resources= new HashSet<>();

}
