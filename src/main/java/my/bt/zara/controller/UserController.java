package my.bt.zara.controller;

import my.bt.zara.dto.UserRegistrationRequest;
import my.bt.zara.model.User;
import my.bt.zara.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  public ResponseEntity<String> registerUser(@RequestBody UserRegistrationRequest request) {
    try {
      User user = userService.registerUser(request.getUsername(), request.getPassword());
      return ResponseEntity.ok("User registered successfully: " + user.getUsername());
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
