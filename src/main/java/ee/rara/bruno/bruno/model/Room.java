package ee.rara.bruno.bruno.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "ROOMS")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROOM_ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ROOM_NAME", length = 50)
    private String roomName;

    @Column(name = "CAPACITY")
    private Integer capacity;

    @Size(max = 150)
    @Column(name = "KEYWORDS", length = 150)
    private String keywords;

    @Column (name = "PRICE")
    private BigDecimal price;

    @Column(name = "FLOOR")
    private Integer floor;

    @Column( name = "ROOM_NUMBER", length = 10)
    private String roomNumber;

    @Column( name = "EQUIPMENT", length = 150)
    private String equipment;

    @Column( name = "CAN_BOOK")
    private Boolean canBook;

    @Column( name = "EXCEPTIONS", length = 150)
    private String exceptions;

    @Column (name = "ROOM_SIZE")
    private BigDecimal roomSize;


}