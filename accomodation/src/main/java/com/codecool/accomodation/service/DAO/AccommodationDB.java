package com.codecool.accomodation.service.DAO;

import com.codecool.accomodation.model.DTO.AccommodationDTO;
import com.codecool.accomodation.model.entity.Accommodation;
import com.codecool.accomodation.repository.AccommodationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
@Data
@RequiredArgsConstructor
public class AccommodationDB implements AccommodationDAO {

    private final AccommodationRepository repository;

    @Override
    public List<Accommodation> findAllByHost(Long hostId) {
        return repository.findAccommodationsByHostHostId(hostId);
    }

    @Override
    public void saveNewAccommodation(AccommodationDTO accommodationDTO) {
        Accommodation accommodation = Accommodation.builder()
            .host(accommodationDTO.getHost())
            .name(accommodationDTO.getName())
            .description(accommodationDTO.getDescription())
            .location(accommodationDTO.getLocation())
            .type((accommodationDTO.getType()))
            .maxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest())
            .rooms(accommodationDTO.getRooms())
            .build();

        repository.save(accommodation);
    }

    @Override
    public void deleteAccommodation(Long accommodationId) {
        repository.deleteAccommodationById(accommodationId);
    }

    @Override
    public Accommodation findAccommodationById(Long accommodationId) {
        return repository.findById(accommodationId)
            .orElseThrow(() -> new NoSuchElementException("No accommondation was found"));
    }

    @Override
    public void updateAccommodation(String accommodationId, AccommodationDTO accommodationDTO) {
        Accommodation toEdit = findAccommodationById(Long.parseLong(accommodationId));

        toEdit.setName(accommodationDTO.getName());
        toEdit.setDescription(accommodationDTO.getDescription());
        toEdit.setMaxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest());
        toEdit.setRooms(accommodationDTO.getRooms());

        repository.save(toEdit);
    }
}
