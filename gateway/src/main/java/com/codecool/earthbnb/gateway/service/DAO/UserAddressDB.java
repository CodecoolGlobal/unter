package com.codecool.earthbnb.gateway.service.DAO;

import com.codecool.earthbnb.gateway.model.DTO.UserAddressDTO;
import com.codecool.earthbnb.gateway.model.entity.UserAddress;
import com.codecool.earthbnb.gateway.model.entity.UserEntity;
import com.codecool.earthbnb.gateway.repository.UserAddressRepository;
import com.codecool.earthbnb.gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class UserAddressDB implements UserAddressDAO{

    private final UserAddressRepository repository;

    private final UserRepository userRepository;

    @Override
    public void saveUserAddress(Long userId, UserAddress userAddress) {
        UserEntity userEntity = userRepository.findDistinctById(userId);
        if(userEntity.getAddress()==null){
            userEntity.setAddress(userAddress);
            userAddress.setUserEntity(userEntity);
            userRepository.save(userEntity);
        } else {
            Long addressId = userEntity.getAddress().getId();
            UserAddress distinctById = repository.findDistinctById(addressId);
            distinctById.setCity(userAddress.getCity());
            distinctById.setCountry(userAddress.getCountry());
           distinctById.setHouseNumber(userAddress.getHouseNumber());
            distinctById.setStreet(userAddress.getStreet());
            distinctById.setZipCode(userAddress.getZipCode());
            repository.save(distinctById);
        }

    }
}
