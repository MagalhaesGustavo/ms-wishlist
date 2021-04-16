package com.challenge.domain;

import com.challenge.intefaces.json.response.GetWishlistResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "Wishlist")
public class WishlistDomain {

  @Id
  private ObjectId id;


  @Field("productId")
  private String productId;

  @Field("nome")
  private String name;

  public GetWishlistResponse toWishlistResponse() {
    return GetWishlistResponse.builder()
        .productId(this.getProductId())
        .nome(this.getName())
        .build();
  }
}