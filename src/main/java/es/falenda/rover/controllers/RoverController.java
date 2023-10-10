package es.falenda.rover.controllers;

import es.falenda.rover.dto.CommandDto;
import es.falenda.rover.dto.RoverDto;
import es.falenda.rover.models.Rover;
import es.falenda.rover.services.RoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rover")
public class RoverController {
    @Autowired
    RoverService roverService;

    @GetMapping
    public ResponseEntity<?> positioRover() {
        return ResponseEntity.ok(roverService.find());
    }

    @PostMapping
    public ResponseEntity<?> createRover(@RequestBody RoverDto roverDTO) {

        return ResponseEntity.ok(roverService.save(new Rover(roverDTO)));
    }


    /*   @PostMapping("/commands")
       public ResponseEntity<?> sendCommand(@RequestBody CommandDto commandDto){
           return ResponseEntity.ok(commandDto);
       }*/
    @PostMapping("/commands")
    public void sendCommand(@RequestBody CommandDto commandDto) {
        for (String command : commandDto.getCommands()) {
            roverService.sendCommand(command);
            System.out.println(command);
        }
    }
}
