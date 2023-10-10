package es.falenda.rover.services;

import es.falenda.rover.models.Obstacle;
import es.falenda.rover.models.Rover;
import es.falenda.rover.repositories.ObstacleRepository;
import es.falenda.rover.repositories.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObstacleSrviceImp implements ObstacleService {
    @Autowired
    ObstacleRepository obstacleRepository;

    @Override
    public List<Obstacle> findAll() {
        return obstacleRepository.findAll();
    }

    @Override
    public Optional<Obstacle> save(Obstacle obstacle) {
        return Optional.of(obstacleRepository.save(obstacle));
    }
}
