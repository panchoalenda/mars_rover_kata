package es.falenda.rover.services;

import es.falenda.rover.models.Obstacle;
import es.falenda.rover.models.Rover;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ObstacleService {
    @Transactional
    List<Obstacle> findAll();

    @Transactional
    Optional<Obstacle> save(Obstacle obstacle);

}
