package ee.rara.bruno.bruno.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

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

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "FLOOR")
    private Integer floor;
}