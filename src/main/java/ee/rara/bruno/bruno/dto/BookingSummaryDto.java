package ee.rara.bruno.bruno.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class BookingSummaryDto {
    private int roomId;
    private String transactionRef;
    private String roomName;
    private BigDecimal roomPrice;

}
