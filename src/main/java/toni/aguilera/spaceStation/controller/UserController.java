package toni.aguilera.spaceStation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import toni.aguilera.spaceStation.service.UserService;
import toni.aguilera.spaceStation.util.RoleEnum;

@RestController
@RequestMapping(value = "api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "")
    public ResponseEntity<?> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping(value = "/{role}")
    public ResponseEntity<?> getUsersByRole(@PathVariable RoleEnum role) {
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }
}
