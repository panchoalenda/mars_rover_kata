package es.falenda.rover.controllers;

import es.falenda.rover.dto.ObstacleDto;
import es.falenda.rover.dto.RoverDto;
import es.falenda.rover.models.Obstacle;
import es.falenda.rover.models.Rover;
import es.falenda.rover.services.ObstacleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/obstacle")
public class ObstacleController {
    @Autowired
    ObstacleService obstacleService;

    @GetMapping
    public ResponseEntity<?> listObstacle() {
        return ResponseEntity.ok(obstacleService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> sendPosition(@RequestBody ObstacleDto obstacleDto) {

        return ResponseEntity.ok(obstacleService.save(new Obstacle(obstacleDto)));
    }


}
