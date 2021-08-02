package toni.aguilera.spaceStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toni.aguilera.spaceStation.service.RoomService;

import javax.xml.ws.Response;
import java.util.UUID;

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

    @PostMapping()
    public ResponseEntity<?> goToRoom(@RequestParam UUID roomId, @RequestParam UUID userId){
        return ResponseEntity.ok(roomService.goToRoom(roomId, userId));
    }

}
