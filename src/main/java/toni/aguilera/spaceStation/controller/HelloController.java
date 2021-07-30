package toni.aguilera.spaceStation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("api/hello")
public class HelloController {

  public ResponseEntity<?> hello() {
    return ResponseEntity.ok("hello world!");
  }
}
