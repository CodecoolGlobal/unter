package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.entity.Coordinate;
import com.codecool.accommodation.model.entity.Location;


public interface LocationDAO {
    Location getLocationByCoordinate(Coordinate coordinate);

}
