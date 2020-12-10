package com.codecool.accommodation.service.DAO;

import com.codecool.accommodation.model.DTO.AccommodationDTO;
import com.codecool.accommodation.model.DTO.RoomDTO;
import com.codecool.accommodation.model.entity.Accommodation;
import com.codecool.accommodation.model.entity.Location;
import com.codecool.accommodation.model.entity.Room;
import com.codecool.accommodation.repository.AccommodationRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Component
@Data
@RequiredArgsConstructor
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

    @Transactional
    @Override
    public void saveNewAccommodation(AccommodationDTO accommodationDTO) {

        Accommodation accommodation2 = new Accommodation();
        accommodation2.setDescription(accommodationDTO.getDescription());
        accommodation2.setHostId(accommodationDTO.getHostId());
        accommodation2.setMaxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest());
        accommodation2.setName(accommodationDTO.getName());
        accommodation2.setType(accommodationDTO.getType());

        long counter = 1;
        for(RoomDTO room : accommodationDTO.getRooms()){
            if(accommodation2.getRooms()== null){
                accommodation2.createRooms();
            }
            Room newRoom = new Room(counter, room.getType());
            newRoom.setAccommodation(accommodation2);
            accommodation2.getRooms().add(newRoom);
            //accommodation2.getRooms().add(new Room(counter, room.getType()));
            counter = counter +1;
        }




//        accommodation2.setLocation(new Location());
        entityManager.setFlushMode(FlushModeType.COMMIT);

        entityManager.persist(accommodation2);



//        Accommodation accommodation = Accommodation.builder()
//                .hostId(accommodationDTO.getHostId())
//                .name(accommodationDTO.getName())
//                .description(accommodationDTO.getDescription())
//                //.location(accommodationDTO.getLocation())
//                .type((accommodationDTO.getType()))
//                .maxNumberOfGuests(accommodationDTO.getMaxNumberOfGuest())
//                //.rooms(accommodationDTO.getRooms())
//                .build();
//
//
//
//
//        repository.saveAndFlush(accommodation);
    }

    @Override
    public void deleteAccommodation(Long accommodationId) {
        repository.deleteAccommodationById(accommodationId);
    }

    @Override
    public Accommodation findAccommodationById(Long accommodationId) {
        return repository.findById(accommodationId)
                .orElseThrow(() -> new NoSuchElementException("No accommodation was found"));
    }

    @Override
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
