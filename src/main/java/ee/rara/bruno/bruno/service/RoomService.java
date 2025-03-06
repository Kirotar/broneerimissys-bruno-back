package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public List<Room> findSearchedRooms(RoomSearch search) {
        return roomRepository.findAvailableRoomsWithFilters(search.getStartDateTime(), search.getEndDateTime(),
                search.getMinCapacity(), search.getFloor(), search.getKeywords());
    }

    public void createRoom(Room room) {
        roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public void updateRoom(int id, Room room) {
        if (room.getId() == id) {
             roomRepository.save(room);
        }
    }
}
