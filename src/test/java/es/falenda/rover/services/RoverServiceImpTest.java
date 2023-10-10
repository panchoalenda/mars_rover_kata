package es.falenda.rover.services;
import es.falenda.rover.controllers.RoverController;
import es.falenda.rover.dto.CommandDto;
import es.falenda.rover.models.Direction;
import es.falenda.rover.models.Rover;
import es.falenda.rover.repositories.RoverRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RoverServiceImpTest {

    @Mock //Creo el objeto falso
    private RoverRepository roverRepository;

    @InjectMocks //Inyecto los objetos falsos
    private RoverServiceImp service;

    @Test
    public void whenSendCommand_callService() {

        Rover rover = new Rover();
        rover.setX(5);
        rover.setY(5);
        rover.setDirection(Direction.NORTH);
        List<Rover> roverList = new ArrayList<>();
        roverList.add(rover);

        when(roverRepository.findAll()).thenReturn(roverList);

        service.sendCommand("F");

        Rover finalRover = new Rover();
        finalRover.setX(5);
        finalRover.setY(4);
        finalRover.setDirection(Direction.NORTH);

        verify(roverRepository, times(1)).save(finalRover);

    }

}
