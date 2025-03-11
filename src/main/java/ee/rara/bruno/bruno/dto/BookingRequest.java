package ee.rara.bruno.bruno.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
public class BookingRequest {
    private int roomId;
    private int userId;
    private Instant startTime;
    private Instant endTime;

}
