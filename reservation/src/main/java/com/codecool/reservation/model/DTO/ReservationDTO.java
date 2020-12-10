package com.codecool.reservation.model.DTO;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationDTO {

    private Long accommodationId;

    private Long guestId;

    private LocalDate startDate;

    private LocalDate endDate;
}
