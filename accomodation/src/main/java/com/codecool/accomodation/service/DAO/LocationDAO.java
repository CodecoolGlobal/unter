package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.Coordinates;
import com.codecool.accomodation.model.entity.Location;

import java.util.List;

public interface LocationDAO {
    List<Location> getLocationInDistance(Coordinates coordinates, Double searchRadius);

}
