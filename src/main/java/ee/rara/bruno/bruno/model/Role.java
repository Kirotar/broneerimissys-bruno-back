package ee.rara.bruno.bruno.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "ROLES")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID", nullable = false)
    private Integer id;

    @Size(max = 50)
    @Column(name = "ROLE", length = 50)
    private String role;

}