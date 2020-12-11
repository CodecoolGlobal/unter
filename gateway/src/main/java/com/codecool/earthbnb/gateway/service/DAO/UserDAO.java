package com.codecool.earthbnb.gateway.service.DAO;

import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import com.codecool.earthbnb.gateway.model.Response;

import javax.servlet.http.HttpServletRequest;

public interface UserDAO {
    PublicUserDTO getPublicUserDataByUserId(Long userId);

    PublicUserDTO getLoggedInUserData(HttpServletRequest request);

    Response register(UserDTO userDTO);


}
