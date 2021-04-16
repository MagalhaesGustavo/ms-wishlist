package com.challenge.builders;

import com.challenge.intefaces.json.request.PostWishlistRequest;

public class PostWishlistRequestBuilder {

  public static PostWishlistRequest createPostWishlistRequestSuccess() {
    return PostWishlistRequest.builder()
        .nome("Garlic")
        .productId("1")
        .build();
  }
}
