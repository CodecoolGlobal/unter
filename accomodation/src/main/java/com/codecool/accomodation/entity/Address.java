package com.codecool.accomodation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Integer houseNumber;

    @Column
    private String street;

    @Column
    private String city;

    @Column
    private String zipCode;

    @OneToOne(mappedBy = "address")
    private Location location;
}
