package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.dto.RoomChangeRequest;
import ee.rara.bruno.bruno.dto.RoomSearch;
import ee.rara.bruno.bruno.model.Room;
import ee.rara.bruno.bruno.service.RoomService;
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
    @Operation(summary = "Kõik ruumid.")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }

    @GetMapping("/search")
    @Operation(summary = "Ruumiotsing")
    public List<Room> getSearchedRooms(@ModelAttribute RoomSearch search) {
        return roomService.findSearchedRooms(search);
    }

    @GetMapping("/get-bookable-rooms")
    @Operation(summary = "Kõik ruumid, mida on võimalik ise broneerida.")
    public List<Room> getAllBookableRooms() {
        return roomService.getAllBookableRooms();
    }

    @GetMapping("/get-query-rooms")
    @Operation(summary = "Kõik ruumid, mida on võimalik päringuga broneerida.")
    public List<Room> getAllQueryFormRooms() {
        return roomService.getAllQueryFormRooms();
    }

    @GetMapping("/is-bookable/{id}")
    @Operation(summary = "Kas saab broneerida jah/ei.")
    public boolean isBookable(@PathVariable int id) {
        return roomService.isRoomBookable(id);
    }

    @PostMapping("/add-room")
    @Operation(summary = "Lisab ruumi.")
    public void createRoom(@RequestBody RoomChangeRequest request) {
        roomService.createRoom(request);
    }

    @DeleteMapping("/delete-room/{id}")
    @Operation(summary = "Kustutab ruumi.")
    public void deleteRoom(@PathVariable("id") int id) {
        roomService.deleteRoom(id);
    }

    @PutMapping("/change-room/{id}")
    @Operation(summary = "Muudab ruumi andmeid.")
    public void updateRoom(@PathVariable("id") int id, @RequestBody RoomChangeRequest updateRequest) {
        roomService.updateRoom(id, updateRequest);
    }
}
