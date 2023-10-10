package es.falenda.rover.models;

import es.falenda.rover.dto.CommandDto;
import es.falenda.rover.dto.RoverDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rover")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pos_x")
    private int x;
    @Column(name = "pos_y")
    private int y;
    @Enumerated(EnumType.STRING)
    private Direction direction;

    public Rover(RoverDto roverDto) {
       this.x = roverDto.x();
       this.y = roverDto.y();
       this.direction = roverDto.direction();
    }

    public Rover(CommandDto commandDto) {
       /*
        this.x = commandDto.getCommands(1);
        this.y = commandDto.getCommands(2);*/
    }
}
