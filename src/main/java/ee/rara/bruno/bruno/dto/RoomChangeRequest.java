package ee.rara.bruno.bruno.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class RoomChangeRequest {

    private String roomName;
    private int capacity;
    private String keywords;
    private BigDecimal price;
    private int floor;
    private String roomNumber;
    private String equipment;
    private String permission;
    private BigDecimal roomSize;

}
