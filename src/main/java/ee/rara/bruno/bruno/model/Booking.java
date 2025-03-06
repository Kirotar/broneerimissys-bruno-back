package ee.rara.bruno.bruno.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "BOOKING")
public class Booking {
    @Id
    @Column(name = "BOOKING_ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "IS_BOOKED")
    private Boolean isBooked;

    @Column(name = "IS_PAID")
    private Boolean isPaid;

    @Column(name = "START_TIME")
    private Instant startTime;

    @Column(name = "END_TIME")
    private Instant endTime;

    @Column(name = "CREATED_AT")
    private Instant createdAt;

    @Column(name = "UPDATED_AT")
    private Instant updatedAt;

}