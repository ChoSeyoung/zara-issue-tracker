package my.bt.zara.exception;

import my.bt.zara.common.ApiResponse;
import my.bt.zara.util.ApiResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(
        ResourceNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
          .body(ApiResponseUtil.createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(),
                "ResourceNotFoundException", ex.getDetails()));
  }
}