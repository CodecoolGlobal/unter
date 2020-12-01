package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.entity.Coordinate;
import com.codecool.accomodation.model.entity.Location;


public interface LocationDAO {
    Location getLocationByCoordinate(Coordinate coordinate);

}
