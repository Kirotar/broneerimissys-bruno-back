package ee.rara.bruno.bruno.service;

import ee.rara.bruno.bruno.dto.RoomChangeRequest;
import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
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

    public List<Room> getAllBookableRooms() {
        return roomRepository.findRoomsByBookingStatus(true);
    }

    public List<Room> getAllQueryFormRooms() {
        return roomRepository.findRoomsByBookingStatus(false);
    }

    public boolean isRoomBookable(int id){
        return roomRepository.findIfCanBook(id);
    }

    public List<Room> findSearchedRooms(RoomSearch search) {
        return roomRepository.findAvailableRoomsWithFilters(search.getStartDateTime(), search.getEndDateTime(),
                search.getMinCapacity(), search.getFloor(), search.getKeywords());
    }

    public void createRoom(RoomChangeRequest request) {
        Room room = new Room();
        room.setRoomName(request.getRoomName());
        room.setCapacity(request.getCapacity());
        room.setFloor(request.getFloor());
        room.setPrice(request.getPrice());
        room.setRoomNumber(request.getRoomNumber());
        room.setEquipment(request.getEquipment());
        room.setCanBook(request.getCanBook());
        room.setRoomSize(request.getRoomSize());
        room.setKeywords(request.getKeywords());
        roomRepository.save(room);
    }

    public void deleteRoom(int id) {
        roomRepository.deleteById(id);
    }

    public void updateRoom(int id, RoomChangeRequest dto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Room not found"));

        if (dto.getRoomName() != null) {
        room.setRoomName(dto.getRoomName());}
        if (dto.getCapacity() > 0) {
        room.setCapacity(dto.getCapacity());}
        if (dto.getFloor() > 0) {
        room.setFloor(dto.getFloor());}
        if (dto.getPrice() != null) {
        room.setPrice(dto.getPrice());}
        if (dto.getRoomNumber() != null) {
        room.setRoomNumber(dto.getRoomNumber());}
        if (dto.getEquipment() != null) {
        room.setEquipment(dto.getEquipment());}
        if (dto.getRoomSize() != null) {
        room.setRoomSize(dto.getRoomSize());}
        if (dto.getKeywords() != null) {
        room.setKeywords(dto.getKeywords());}
        if (dto.getCanBook() != null) {
        room.setCanBook(dto.getCanBook());}

             roomRepository.save(room);
    }
}
