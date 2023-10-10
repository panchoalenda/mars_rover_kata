package es.falenda.rover.dto;

import es.falenda.rover.models.Map;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record ObstacleDto(int x, int y, Map map ) {

}
