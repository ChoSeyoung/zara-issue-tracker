package my.bt.zara.util;

import my.bt.zara.common.ApiError;
import my.bt.zara.common.ApiResponse;
import my.bt.zara.common.MetaData;
import org.springframework.http.HttpStatus;

public class ApiResponseUtil {

  public static <T> ApiResponse<T> createSuccessResponse(T data, String message, MetaData meta) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setStatus("success");
    response.setCode(HttpStatus.OK.value());
    response.setMessage(message);
    response.setData(data);
    response.setMeta(meta);
    return response;
  }

  public static <T> ApiResponse<T> createSuccessResponse(T data, String message) {
    return createSuccessResponse(data, message, null);
  }

  public static <T> ApiResponse<T> createErrorResponse(HttpStatus status, String message, String errorType, String errorDetails) {
    ApiResponse<T> response = new ApiResponse<>();
    response.setStatus("error");
    response.setCode(status.value());
    response.setMessage(message);

    ApiError error = new ApiError();
    error.setType(errorType);
    error.setDetails(errorDetails);
    response.setError(error);

    return response;
  }
}
