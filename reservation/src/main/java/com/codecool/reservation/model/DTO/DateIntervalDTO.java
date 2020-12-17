package com.codecool.reservation.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class DateIntervalDTO {
    private LocalDate startDate;
    private LocalDate endDate;
}
