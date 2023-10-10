package es.falenda.rover.controllers;

import es.falenda.rover.dto.CommandDto;
import es.falenda.rover.services.RoverService;
import es.falenda.rover.services.RoverServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class RoverControllerTest {
    @Mock //Creo el objeto falso
    private RoverService roverService;

    @InjectMocks //Inyecto los objetos falsos
    private RoverController roverController;

    @Test
    public void whenSendCommand_callService() {
        CommandDto commandDto = new CommandDto();
        List<String> commandsList = new ArrayList<>();
        commandsList.add("R");
        commandDto.setCommands(commandsList);

        roverController.sendCommand(commandDto);

        verify(roverService, times(1)).sendCommand("F");

    }

    @Test
    public void whenSendCommand_FRF() {
        CommandDto commandDto = new CommandDto();
        List<String> commandsList = new ArrayList<>();
        commandsList.add("F");
        commandsList.add("R");
        commandsList.add("F");
        commandDto.setCommands(commandsList);

        roverController.sendCommand(commandDto);

        verify(roverService, times(3)).sendCommand(any());

    }

}
