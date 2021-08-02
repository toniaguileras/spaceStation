package toni.aguilera.spaceStation.util.exception;

import org.springframework.http.HttpStatus;

public abstract class SpaceStationException extends RuntimeException {
  private final String errorCode;
  private final String errorMessage;
  private final HttpStatus httpStatus;

  public SpaceStationException(String errorCode, String errorMessage, HttpStatus httpStatus) {
    super(errorMessage);
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.httpStatus = httpStatus;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public String getErrorMessage() {
    return errorMessage;
  }

  public HttpStatus getHttpStatus() {
    return httpStatus;
  }
}
