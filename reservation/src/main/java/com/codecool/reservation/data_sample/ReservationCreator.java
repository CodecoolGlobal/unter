package com.codecool.reservation.data_sample;

import com.codecool.reservation.model.DTO.ReservationDTO;
import com.codecool.reservation.model.entity.Reservation;
import com.codecool.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ReservationCreator {

    private final ReservationRepository reservationRepository;

    public void initialize() {

        //march

        long[] accommodationIds = {1, 4, 7, 10, 13, 16, 19};
        long[] guestIds = {1, 2, 3, 4, 5, 6, 7};
        LocalDate[] startDates = {LocalDate.parse("2021-03-21"), LocalDate.parse("2021-03-23"), LocalDate.parse("2021-03-24"), LocalDate.parse("2021-03-15"), LocalDate.parse("2021-03-22"), LocalDate.parse("2021-03-05"), LocalDate.parse("2021-03-10"),};
        LocalDate[] endDates = {LocalDate.parse("2021-03-25"), LocalDate.parse("2021-03-26"), LocalDate.parse("2021-03-27"), LocalDate.parse("2021-03-20"), LocalDate.parse("2021-03-27"), LocalDate.parse("2021-03-11"), LocalDate.parse("2021-03-15"),};

        for(int i = 0; i < accommodationIds.length; i++){
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .accommodationId(accommodationIds[i])
                    .guestId(guestIds[i])
                    .startDate(startDates[i])
                    .endDate(endDates[i])
                    .build();

            Reservation reservation = createReservation(reservationDTO);
            reservationRepository.saveAndFlush(reservation);
        }

        //april
        LocalDate[] startDates2 = {LocalDate.parse("2021-03-29"), LocalDate.parse("2021-04-02"), LocalDate.parse("2021-04-11"), LocalDate.parse("2021-04-06"), LocalDate.parse("2021-04-01"), LocalDate.parse("2021-04-03"), LocalDate.parse("2021-04-05"),};
        LocalDate[] endDates2 = {LocalDate.parse("2021-04-05"), LocalDate.parse("2021-04-07"), LocalDate.parse("2021-04-15"), LocalDate.parse("2021-04-10"), LocalDate.parse("2021-04-07"), LocalDate.parse("2021-04-07"), LocalDate.parse("2021-04-13"),};

        for(int i = 0; i < accommodationIds.length; i++){
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .accommodationId(accommodationIds[i])
                    .guestId(guestIds[i])
                    .startDate(startDates2[i])
                    .endDate(endDates2[i])
                    .build();

            Reservation reservation = createReservation(reservationDTO);
            reservationRepository.saveAndFlush(reservation);
        }

        //may
        LocalDate[] startDates3 = {LocalDate.parse("2021-05-09"), LocalDate.parse("2021-05-02"), LocalDate.parse("2021-05-11"), LocalDate.parse("2021-04-20"), LocalDate.parse("2021-04-28"), LocalDate.parse("2021-04-25"), LocalDate.parse("2021-05-01"),};
        LocalDate[] endDates3 = {LocalDate.parse("2021-05-15"), LocalDate.parse("2021-05-10"), LocalDate.parse("2021-05-15"), LocalDate.parse("2021-04-25"), LocalDate.parse("2021-05-06"), LocalDate.parse("2021-05-01"), LocalDate.parse("2021-05-09"),};

        for(int i = 0; i < accommodationIds.length; i++){
            ReservationDTO reservationDTO = ReservationDTO.builder()
                    .accommodationId(accommodationIds[i])
                    .guestId(guestIds[i])
                    .startDate(startDates3[i])
                    .endDate(endDates3[i])
                    .build();

            Reservation reservation = createReservation(reservationDTO);
            reservationRepository.saveAndFlush(reservation);
        }

    }

    private Reservation createReservation(ReservationDTO reservationDTO){
        Reservation reservation = Reservation.builder()
                .accommodationId(reservationDTO.getAccommodationId())
                .guestId(reservationDTO.getGuestId())
                .startDate(reservationDTO.getStartDate())
                .endDate(reservationDTO.getEndDate())
                .build();
        return reservation;
    }
}
