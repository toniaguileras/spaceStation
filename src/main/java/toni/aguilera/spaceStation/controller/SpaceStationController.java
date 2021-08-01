package toni.aguilera.spaceStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toni.aguilera.spaceStation.service.SpaceStationService;

@RestController
@RequestMapping("/api/spacestation")
public class SpaceStationController {

    private final SpaceStationService spaceStationService;

    @Autowired
    public SpaceStationController(SpaceStationService spaceStationService) {
        this.spaceStationService = spaceStationService;
    }

    @PostMapping(value = "/launch")
    public ResponseEntity<?> launchRocket() {
        return ResponseEntity.ok(spaceStationService.launch());
    }
}
