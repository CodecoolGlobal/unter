package com.codecool.earthbnb.gateway.service.DAO;

import com.codecool.earthbnb.gateway.model.DTO.UserAddressDTO;
import com.codecool.earthbnb.gateway.model.entity.UserAddress;

public interface UserAddressDAO {
    void saveUserAddress(Long userId, UserAddress userAddress);
}
