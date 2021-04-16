package com.challenge.intefaces;

public interface Messages {

  // 400
  String FIELD_VALIDATION = "400.001";
  String JSON_VALIDATION = "400.002";
  String REQUIRED_PARAM = "400.003";
  String INVALID_PARAM = "400.004";
  String REQUIRED_REQUEST_BODY = "400.005";
  String INVALID_FIELD = "400.006";
  String REQUIRED_HEADER = "400.007";

  // 404
  String WISH_LIST_ID_NOT_FOUND = "404.001";

  // 422
  String PRODUCT_CANNOT_BE_ADD = "422.001";

  // 500
  String INTERNAL_ERROR = "500.001";
}
