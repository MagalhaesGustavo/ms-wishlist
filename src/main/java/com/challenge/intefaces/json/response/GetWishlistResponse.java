package com.challenge.intefaces.json.response;

import com.challenge.domain.WishlistDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetWishlistResponse {

  private String productId;

  private String nome;

  public static GetWishlistResponse valueOf(WishlistDomain wishlistDomain) {
    return GetWishlistResponse.builder()
        .productId(wishlistDomain.getProductId())
        .nome(wishlistDomain.getName())
        .build();
  }
}
