package es.falenda.rover.models;

import es.falenda.rover.dto.ObstacleDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "obstacle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Obstacle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pos_x")
    private int x;
    @Column(name = "pos_y")
    private int y;

    public Obstacle(ObstacleDto obstacleDto) {
        this.x = obstacleDto.x();
        this.y = obstacleDto.y();

    }
}
