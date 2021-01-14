package com.codecool.reservation.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class RabbitMQDTO {

    private List<Long> ids;

    private LocalDate startDate;

    private LocalDate endDate;
}
