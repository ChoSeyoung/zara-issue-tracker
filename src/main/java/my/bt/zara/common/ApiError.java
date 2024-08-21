package my.bt.zara.common;

import lombok.Data;

@Data
public class ApiError {
  private String type;
  private String details;
}
