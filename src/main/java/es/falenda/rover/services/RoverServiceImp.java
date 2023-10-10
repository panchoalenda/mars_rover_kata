package es.falenda.rover.services;

import es.falenda.rover.models.Direction;
import es.falenda.rover.models.Obstacle;
import es.falenda.rover.models.Rover;
import es.falenda.rover.repositories.ObstacleRepository;
import es.falenda.rover.repositories.RoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoverServiceImp implements RoverService {
    @Autowired
    RoverRepository roverRepository;

    @Autowired
    ObstacleRepository obstacleRepository;

    @Override
    @Transactional
    public Rover find() {
        List<Rover> roverList = roverRepository.findAll();
        return roverList.get(0);
    }

    @Override
    @Transactional
    public Optional<Rover> save(Rover rover) {
        return Optional.of(roverRepository.save(rover));
    }

    @Override
    public void sendCommand(String command) {
        Rover rover = find();
        roverRepository.save(rover);

        switch (command) {
            case "F":
                moveRover(rover, true);
                break;
            case "B":
                moveRover(rover, false);
                break;
            case "R":
                turnRover(rover, true);
                break;
            case "L":
                turnRover(rover, false);
                break;
        }
    }

    private void turnRover(Rover rover, boolean isRight) {
        Direction direction = rover.getDirection();
        Direction finalDirection = null;

        if (isRight) {
            switch (direction) {
                case NORTH -> finalDirection = Direction.EAST;
                case EAST -> finalDirection = Direction.SOUTH;
                case SOUTH -> finalDirection = Direction.WEST;
                case WEST -> finalDirection = Direction.NORTH;
            }
        } else {
            switch (direction) {
                case NORTH -> finalDirection = Direction.WEST;
                case WEST -> finalDirection = Direction.SOUTH;
                case SOUTH -> finalDirection = Direction.EAST;
                case EAST -> finalDirection = Direction.NORTH;
            }
        }
        rover.setDirection(finalDirection);
    }

    private void moveRover(Rover rover, boolean isForward) {
        int posXFinal = rover.getX();
        int posYFinal = rover.getY();

        Direction direction = rover.getDirection();

        if (Direction.EAST.equals(direction)
          || Direction.WEST.equals(direction)) {
            posXFinal -= isForward ? direction.getValue() : -direction.getValue();
        }

        if (Direction.NORTH.equals(direction)
          || Direction.SOUTH.equals(direction)) {
            posYFinal -= isForward ? direction.getValue() : -direction.getValue();
        }

        if (canMoveToThisPosition(posXFinal, posYFinal)) {
            rover.setX(posXFinal);
            rover.setY(posYFinal);
        }
    }

    private boolean canMoveToThisPosition(int posXFinal, int posYFinal) {
        List<Obstacle> obstacles = obstacleRepository.findAll();
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getX() == posXFinal && obstacle.getY() == posYFinal) {
                return false;
            }
        }
        return true;
    }
}
