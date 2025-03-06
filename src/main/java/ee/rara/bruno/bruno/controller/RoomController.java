package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/search")
    public List<Room> getSearchedRooms(@ModelAttribute RoomSearch search) {
        return roomService.findSearchedRooms(search);
    }


    @PostMapping("/add-room")
    public void createRoom(@Valid Room room) {
        roomService.createRoom(room);
    }

    @DeleteMapping("/delete-room/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/change-room/{id}")
    public void updateRoom(@PathVariable("id") int id, @Valid Room room) {
        roomService.updateRoom(id, room);
    }
}
