package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.RoomChangeRequest;
import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.service.RoomService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Kõik ruumid")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }


    @GetMapping("/search")
    public List<Room> getSearchedRooms(@ModelAttribute RoomSearch search) {
        return roomService.findSearchedRooms(search);
    }


    @PostMapping("/add-room")
    public void createRoom(@RequestBody RoomChangeRequest request) {
        roomService.createRoom(request);
    }

    @DeleteMapping("/delete-room/{id}")
    public void deleteRoom(@PathVariable("id") int id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/change-room/{id}")
    public void updateRoom(@PathVariable("id") int id, @RequestBody RoomChangeRequest updateRequest) {
        roomService.updateRoom(id, updateRequest);
    }
}
