package com.challenge.builders;

import com.challenge.domain.WishlistDomain;
import org.bson.types.ObjectId;

public class WishlistDomainBuilder {

  public static WishlistDomain createWishlistDomainSuccess() {
    return WishlistDomain.builder()
        .id(new ObjectId())
        .name("Garlic")
        .productId("1")
        .build();
  }

  public static WishlistDomain createWishlistDomainEmpty() {
    return WishlistDomain.builder()
        .id(new ObjectId())
        .build();
  }
}