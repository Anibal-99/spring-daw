package com.trantorinc.springbootlocaldevdocker.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "client")
public class Client {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "surname")
    @Getter
    @Setter
    private String surname;

    @Column(name = "dni")
    @Getter
    @Setter
    private Integer dni;

    public String toString() {
        return "Client - Id: " + id + ", Name: " + name;
    }
}
