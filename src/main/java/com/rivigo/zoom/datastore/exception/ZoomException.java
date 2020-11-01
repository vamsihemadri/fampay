package com.rivigo.zoom.datastore.exception;

public class ZoomException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public ZoomException() {
    super();
  }

  public ZoomException(String message, Throwable cause) {
    super(message, cause);
  }

  public ZoomException(String message) {
    super(message);
  }

  public ZoomException(String message, Object... variables) {
    super(String.format(message, variables));
  }

  public ZoomException(Throwable cause) {
    super(cause);
  }
}
