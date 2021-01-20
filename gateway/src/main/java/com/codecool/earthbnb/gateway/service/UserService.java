package com.codecool.earthbnb.gateway.service;

import com.codecool.earthbnb.gateway.exception.UserNotExistsException;
import com.codecool.earthbnb.gateway.model.DTO.ProfileDTO;
import com.codecool.earthbnb.gateway.model.DTO.PublicUserDTO;
import com.codecool.earthbnb.gateway.model.DTO.UserDTO;
import com.codecool.earthbnb.gateway.model.Response;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import com.codecool.earthbnb.gateway.model.entity.types.Gender;
import com.codecool.earthbnb.gateway.service.DAO.UserAddressDAO;
import com.codecool.earthbnb.gateway.service.DAO.UserDAO;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.apache.commons.validator.routines.EmailValidator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    @PersistenceContext
    private EntityManager entityManager;

    private final UserDAO userDAO;

    private final UserAddressDAO userAddressDAO;

    public UserEntity getPublicUserDataByEmail(String email){
        return userDAO.getPublicUserDataByEmail(email);
    }

    public PublicUserDTO getPublicUserDataByUserId(Long userId) {
        if(userDAO.getPublicUserDataByUserId(userId) == null ){
            throw new UserNotExistsException();
        }
        return userDAO.getPublicUserDataByUserId(userId);
    }

    public PublicUserDTO getLoggedInUserData(HttpServletRequest request) {
        if(userDAO.getLoggedInUserData(request) == null){
            throw new UserNotExistsException();
        }
        return userDAO.getLoggedInUserData(request);
    }

    public boolean register(UserDTO userDTO) {
       return userDAO.register(userDTO);
    }

    @Transactional
    public void saveProfileData(Long userId, ProfileDTO profileDTO) {
        if(profileDTO.getAddress() !=null){
            userAddressDAO.saveUserAddress(userId, profileDTO.getAddress());
        }

        Map<String, Object> parameterMap = new HashMap<>();
        List<String> setClause = new ArrayList<>();

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE UserEntity u SET  ");

        if (!profileDTO.getFullName().isEmpty()){
            String[] names = profileDTO.getFullName().split(" ");
            setClause.add(" u.firstName =:firstName,");
            parameterMap.put("firstName", names[0]);
            setClause.add(" u.lastName =:lastName,");
            parameterMap.put("lastName", names[1]);
        }
        if(profileDTO.getBirthDate() != null){
            setClause.add(" u.birthDate =:birthDate,");
            parameterMap.put("birthDate", profileDTO.getBirthDate());
        }
        if(profileDTO.getEmail() != null){
            setClause.add(" u.email =:email,");
            parameterMap.put("email", profileDTO.getEmail());
        }
        if(profileDTO.getGender() != Gender.NONE){
            setClause.add(" u.gender =:gender,");
            parameterMap.put("gender", profileDTO.getGender());
        }
        if(profileDTO.getPhoneNumber() != null){
            setClause.add(" u.phoneNumber =:phoneNumber");
            parameterMap.put("phoneNumber", profileDTO.getPhoneNumber());
        }


        queryBuilder.append(String.join("", setClause));

        if(queryBuilder.length()>0){
            if(queryBuilder.charAt(queryBuilder.length()-1) == ',')
                queryBuilder.deleteCharAt(queryBuilder.length()-1);
        }

        queryBuilder.append(" WHERE u.id = :userId");
        Query jpaQuery = entityManager.createQuery(queryBuilder.toString());
        jpaQuery.setParameter("userId", userId);

        for(String key :parameterMap.keySet()) {
            jpaQuery.setParameter(key, parameterMap.get(key));
        }

        jpaQuery.executeUpdate();

    }
}
