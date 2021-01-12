package com.codecool.earthbnb.gateway.service;

import com.codecool.earthbnb.gateway.exception.UserNotExistsException;
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
        if(dao.getPublicUserDataByUserId(userId) == null ){
            throw new UserNotExistsException();
        }
        return dao.getPublicUserDataByUserId(userId);
    }

    public PublicUserDTO getLoggedInUserData(HttpServletRequest request) {
        if(dao.getLoggedInUserData(request) == null){
            throw new UserNotExistsException();
        }
        return dao.getLoggedInUserData(request);
    }

    public boolean register(UserDTO userDTO) {
       return dao.register(userDTO);
    }
}
