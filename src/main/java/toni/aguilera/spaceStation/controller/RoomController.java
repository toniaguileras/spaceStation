package toni.aguilera.spaceStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import toni.aguilera.spaceStation.service.RoomService;

import javax.xml.ws.Response;

@RestController
@RequestMapping(value = "api/room")
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping()
    public ResponseEntity<?> getUsersInRooms(){
        return ResponseEntity.ok(roomService.getUsersInRooms());
    }
}
