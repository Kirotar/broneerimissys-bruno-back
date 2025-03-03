package ee.rara.bruno.bruno.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EDUCATIONAL_EVENTS")
public class EducationalEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EVENT_ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "EVENT_NAME", length = 50)
    private String eventName;

    @Column(name = "EVENT_CAPACITY")
    private Integer eventCapacity;

}