package es.falenda.rover.services;

import es.falenda.rover.dto.RoverDto;
import es.falenda.rover.models.Rover;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface RoverService {
    @Transactional
    Rover find();

    @Transactional
    Optional<Rover> save(Rover rover);

    @Transactional
    void sendCommand(String command);
}
