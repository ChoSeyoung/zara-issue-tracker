package my.bt.zara.controller;

import my.bt.zara.common.ApiResponse;
import my.bt.zara.dto.UserRegistrationRequest;
import my.bt.zara.model.Users;
import my.bt.zara.service.UserService;
import my.bt.zara.util.ApiResponseUtil;
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
  public ResponseEntity<ApiResponse<Users>> registerUser(
        @RequestBody UserRegistrationRequest params) {

    Users user = userService.registerUser(params);

    return ResponseEntity.ok(
          ApiResponseUtil.createSuccessResponse(user, "ok"));
  }
}
