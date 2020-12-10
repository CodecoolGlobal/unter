package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.RoomDTO;
import com.codecool.accommodation.model.entity.*;
import com.codecool.accommodation.repository.AccommodationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

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

    //@Transactional
    @Override
    public void saveNewAccommodation(AccommodationDTO accommodationDTO) {

        Coordinate coordinate = new Coordinate(accommodationDTO.getCoordinate().getLongitude(), accommodationDTO.getCoordinate().getLatitude());

        Address address = accommodationDTO.getAddress();
        //Location location = accommodationDTO.getLocation();

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setDescription(accommodationDTO.getDescription());
        accommodation2.setHostId(accommodationDTO.getHostId());
        accommodation2.setMaxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest());
        accommodation2.setName(accommodationDTO.getName());
        accommodation2.setType(accommodationDTO.getType());
        accommodation2.setAddress(address);
        accommodation2.setCoordinate(coordinate);
        //accommodation2.setLocation(location);


        Session session = entityManager.unwrap(Session.class);
        Transaction tx = session.beginTransaction();

        for(Room room : accommodationDTO.getRooms()){
            if(accommodation2.getRooms()== null){
                accommodation2.createRooms();
            }
            Room newRoom = new Room(room.getType());
            newRoom.setAccommodation(accommodation2);
            newRoom.setBeds(room.getBeds());

            session.save(newRoom);
            accommodation2.getRooms().add(newRoom);
        }


        //coordinate.setLocation(location);


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
    public Accommodation findAccommodationById(Long accommodationId) {

        return repository.findById(accommodationId).get();
    }

    @Override
    @Transactional
    public void updateAccommodation(Long accommodationId, AccommodationDTO accommodationDTO) {
        Accommodation toEdit = findAccommodationById(accommodationId);

        toEdit.setName(accommodationDTO.getName());
        toEdit.setDescription(accommodationDTO.getDescription());
        toEdit.setMaxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest());

        //toEdit.setRooms(accommodationDTO.getRooms());
        repository.save(toEdit);

    }

    @Override
    public boolean isExisted(Long accommodationId) {
        return repository.existsById(accommodationId);
    }
}
