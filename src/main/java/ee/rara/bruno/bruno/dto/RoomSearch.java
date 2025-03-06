package ee.rara.bruno.bruno.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class RoomSearch {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDateTime;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime endDateTime;

    private Integer minCapacity;

    private Integer floor;

    //Currently room table rooms don't have good keywords
    private String keywords;

    // Currently not in use, ideas for future
   /* private BigDecimal maxPrice;
    private List<String> requiredEquipment;
    private String roomType;
    private Boolean hasVideoConference;
    private Boolean hasWhiteboard;
    private Boolean isAccessible;*/

}
