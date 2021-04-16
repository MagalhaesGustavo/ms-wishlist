package com.challenge.exceptions;

import com.challenge.exceptions.MessageError.ApiError;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.assertj.core.util.Lists;

@Getter
@EqualsAndHashCode(callSuper = false)
@ToString
public class UnprocessableEntityException extends RuntimeException {

  private final List<ApiError> errors;

  public UnprocessableEntityException(ApiError error) {
    this(Lists.newArrayList(error));
  }

  public UnprocessableEntityException(List<ApiError> errors) {
    super(errors.toString());
    this.errors = errors;
  }
}
