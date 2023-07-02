package com.trantorinc.springbootlocaldevdocker.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.HashSet;
import java.util.*;

@Entity
@Table(name = "place")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "place_resource",
        joinColumns ={
            @JoinColumn(name = "place_id", referencedColumnName = "id")
        },
        inverseJoinColumns = {
            @JoinColumn(name="resource_id", referencedColumnName = "id")
        }
    )
    @Column(name="place_resource")
    @JsonManagedReference
    private List<Resource> resources;

    public void addResource(Resource resource) {
        this.resources.add(resource);
        resource.getPlaces().add(this);
    }
 
    public void removeResource(Resource resource) {
        this.resources.remove(resource);
        resource.getPlaces().remove(this);
    }
}
