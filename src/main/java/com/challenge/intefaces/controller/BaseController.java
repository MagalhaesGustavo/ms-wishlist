package com.challenge.intefaces.controller;

import static com.challenge.utils.Constants.ACCEPT_RANGE;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface BaseController {

  default ResponseEntity<Void> created(String id) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .header("id", id)
        .build();
  }

  default <T> ResponseEntity<T> ok(T body) {
    return ResponseEntity.ok(body);
  }

  default <T> ResponseEntity<List<T>> getResult(Page<T> page) {

    ResponseEntity<List<T>> responseEntity = ResponseEntity.noContent().build();

    if (ObjectUtils.isNotEmpty(page.getContent())) {

      HttpStatus httpStatus =
          page.getTotalElements() > page.getContent().size() ? HttpStatus.PARTIAL_CONTENT : HttpStatus.OK;

      responseEntity = ResponseEntity.status(httpStatus)
          .header("content-range", String.valueOf(page.getTotalElements()))
          .header("content-pages", String.valueOf(page.getTotalPages()))
          .header("accept-range", String.valueOf(ACCEPT_RANGE))
          .body(page.getContent());
    }

    return responseEntity;
  }

  default ResponseEntity<Void> noContent() {
    return ResponseEntity.noContent().build();
  }
}
