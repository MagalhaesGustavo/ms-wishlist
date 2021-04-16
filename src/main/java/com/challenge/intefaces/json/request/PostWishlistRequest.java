package com.challenge.intefaces.json.request;

import com.challenge.domain.WishlistDomain;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostWishlistRequest {

  @NotBlank
  private String productId;

  @NotBlank
  private String nome;

  public WishlistDomain toPostWishlistDomain() {
    return WishlistDomain.builder()
        .productId(this.productId)
        .name(this.nome)
        .build();
  }
}
