package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.CoordinateDTO;
import com.codecool.accommodation.model.entity.Coordinate;

import java.util.List;

public interface CoordinateDAO {
    List<Coordinate> getAllByDistanceFromCoordinate(CoordinateDTO coordinates, Double distance);
}
