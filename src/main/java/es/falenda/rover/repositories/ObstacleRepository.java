package es.falenda.rover.repositories;

import es.falenda.rover.models.Obstacle;
import es.falenda.rover.models.Rover;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
}
