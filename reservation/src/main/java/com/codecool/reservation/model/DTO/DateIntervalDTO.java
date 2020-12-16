package com.codecool.reservation.model.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DateIntervalDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
