package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.DTO.CoordinateDTO;
import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.repository.CoordinateRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Data
@RequiredArgsConstructor
public class CoordinateDaoDB implements CoordinateDAO {
    private final CoordinateRepository repository;


    @Override
    public List<Coordinate> getAllByDistanceFromCoordinate(CoordinateDTO coordinate, Double distance) {
        return repository.getAllByLongitudeBetweenAndLatitudeBetween(
                coordinate.getLongitude() - distance,
                coordinate.getLongitude() + distance,
                coordinate.getLatitude() - distance,
                coordinate.getLatitude() + distance
        );
    }
}
