package com.codecool.accomodation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Host {

    @Id
    @GeneratedValue
    @Column(name="host_id")
    private Long hostId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "host")
    @Column(nullable = false)
    private List<Accomodation> accomodations;
}
