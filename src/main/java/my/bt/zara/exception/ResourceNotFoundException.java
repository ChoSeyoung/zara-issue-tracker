package my.bt.zara.exception;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

  private String details;

  public ResourceNotFoundException(String message) {
    super(message);
  }

  public ResourceNotFoundException(String message, String details) {
    super(message);
    this.details = details;
  }
}
