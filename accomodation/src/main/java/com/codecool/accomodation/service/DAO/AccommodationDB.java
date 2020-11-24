package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.repository.AccommodationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor
public class AccommodationDB implements AccommodationDAO {

    private final AccommodationRepository repository;
}
