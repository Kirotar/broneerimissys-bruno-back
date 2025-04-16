package ee.rara.bruno.bruno.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BookingDto {
    private int bookingId;
    private int roomId;
    private int roomFloor;
    private String roomName;
    private Instant startTime;
    private Instant endTime;
}