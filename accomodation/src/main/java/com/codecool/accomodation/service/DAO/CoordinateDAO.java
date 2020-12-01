package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.DTO.CoordinateDTO;
import com.codecool.accomodation.model.entity.Coordinate;

import java.util.List;

public interface CoordinateDAO {
    List<Coordinate> getAllByDistanceFromCoordinate(CoordinateDTO coordinates, Double distance);
}
