package ee.rara.bruno.bruno.controller;

import ee.rara.bruno.bruno.service.PINService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pin")
public class PINController {

    private PINService pinService;
    public PINController(PINService pinService) {
        this.pinService = pinService;
    }

    @GetMapping("/{id}")
    public String createBookingPinByBookingId(@PathVariable("id") int id) {
        return pinService.createBookingPinByBookingId(id);
    }


}
