package com.codecool.accomodation.service;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DTOCreator {

    private final ModelMapper modelMapper;

}
