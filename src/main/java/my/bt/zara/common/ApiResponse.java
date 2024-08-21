package my.bt.zara.common;

import lombok.Data;

@Data
public class ApiResponse<T> {
  private String status;
  private int code;
  private String message;
  private T data;
  private ApiError error;
  private MetaData meta;
}
