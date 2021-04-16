package com.challenge.exceptions;

import com.challenge.exceptions.MessageError.ApiError;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class NotFoundException extends RuntimeException {

  private final ApiError error;

  public NotFoundException(ApiError error) {
    super(error.toString());
    this.error = error;
  }
}
