package com.codecool.accomodation.service;

import com.codecool.accomodation.service.DAO.AccommodationDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final AccommodationDAO dao;
}
