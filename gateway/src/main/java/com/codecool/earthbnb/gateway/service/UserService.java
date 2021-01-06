package com.codecool.earthbnb.gateway.service;

import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import com.codecool.earthbnb.gateway.model.Response;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import com.codecool.earthbnb.gateway.service.DAO.UserDAO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDAO dao;

    public UserEntity getPublicUserDataByEmail(String email){
        return dao.getPublicUserDataByEmail(email);
    }

    public PublicUserDTO getPublicUserDataByUserId(Long userId) {
        return dao.getPublicUserDataByUserId(userId);
    }

    public PublicUserDTO getLoggedInUserData(HttpServletRequest request) {
        return dao.getLoggedInUserData(request);
    }

    public Response register(UserDTO userDTO) {
       return dao.register(userDTO);
    }
}
