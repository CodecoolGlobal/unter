package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.exception.AccommodationNotFoundException;
import com.codecool.accommodation.model.DTO.NewAccommodationDTO;
import com.codecool.accommodation.model.DTO.ResponseAccDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.model.entity.types.AccommodationType;
import com.codecool.accommodation.model.entity.types.BedType;
import com.codecool.accommodation.model.entity.types.RoomType;
import com.codecool.accommodation.repository.AccommodationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Data
@RequiredArgsConstructor
@Slf4j
public class AccommodationDB implements AccommodationDAO {

    private final AccommodationRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Accommodation> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Accommodation> findAllByHostId(Long hostId) {

        return repository.findAccommodationsByHostId(hostId);
    }

    @Override
    public void saveNewAccommodation(NewAccommodationDTO newAccommodationDTO) {

        Coordinate coordinate = new Coordinate(newAccommodationDTO.getCoordinate().getLongitude(), newAccommodationDTO.getCoordinate().getLatitude());

        Address address = newAccommodationDTO.getAddress();

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setDescription(newAccommodationDTO.getDescription());
        accommodation2.setHostId(newAccommodationDTO.getHostId());
        accommodation2.setMaxNumberOfGuests(newAccommodationDTO.getMaxNumberOfGuest());
        accommodation2.setName(newAccommodationDTO.getName());
        accommodation2.setType(newAccommodationDTO.getType());
        accommodation2.setAddress(address);
        accommodation2.setCoordinate(coordinate);

        Session session = entityManager.unwrap(Session.class);
        Transaction tx = session.beginTransaction();

        for(Room room : newAccommodationDTO.getRooms()){
            if(accommodation2.getRooms()== null){
                accommodation2.createRooms();
            }
            Room newRoom = new Room(room.getType());
            newRoom.setAccommodation(accommodation2);
            newRoom.setBeds(room.getBeds());

            session.save(newRoom);
            accommodation2.getRooms().add(newRoom);
        }

        session.save(accommodation2);
        session.save(address);
        tx.commit();
        session.close();
    }

    @Override
    @Transactional
    public void deleteAccommodation(Long accommodationId) {
        repository.deleteAccommodationById(accommodationId);
    }

    @Override
    public ResponseAccDTO findAccommodationById(Long accommodationId) {
        Accommodation accommodation = repository.findById(accommodationId).orElseThrow(()-> new AccommodationNotFoundException(accommodationId));

        ResponseAccDTO newAccommodationDTO = ResponseAccDTO.builder()
                .id(accommodation.getId())
                .description(accommodation.getDescription())
                .hostId(accommodation.getHostId())
                .address(accommodation.getAddress())
                .pictures(accommodation.getPictures())
                .rooms(accommodation.getRooms())
                .maxNumberOfGuest(accommodation.getMaxNumberOfGuests())
                .name(accommodation.getName())
                .rooms(accommodation.getRooms())
                .type(accommodation.getType())
                .build();

        newAccommodationDTO.setMyCoordinates(new Coordinate(accommodation.getCoordinate().getLongitude(), accommodation.getCoordinate().getLatitude()));

        return newAccommodationDTO;
    }

    @Override
    @Transactional
    public void updateAccommodation(Long accommodationId, NewAccommodationDTO newAccommodationDTO) {
        Accommodation toEdit = repository.findAccommodationById(accommodationId);

        toEdit.setName(newAccommodationDTO.getName());
        toEdit.setDescription(newAccommodationDTO.getDescription());
        toEdit.setMaxNumberOfGuests(newAccommodationDTO.getMaxNumberOfGuest());

        repository.save(toEdit);

    }

    @Override
    public boolean isExisted(Long accommodationId) {
        return repository.existsById(accommodationId);
    }

    @Override
    public List<String> findAllAccommodationTypes() {
        return Stream.of(AccommodationType.values())
            .map(AccommodationType::name)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllRoomTypes() {
        return Stream.of(RoomType.values())
            .map(RoomType::name)
            .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBedTypes() {
        return Stream.of(BedType.values())
            .map(BedType::name)
            .collect(Collectors.toList());
    }
}
