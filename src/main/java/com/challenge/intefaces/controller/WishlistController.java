package com.challenge.intefaces.controller;

import com.challenge.domain.WishlistDomain;
import com.challenge.intefaces.json.request.PostWishlistRequest;
import com.challenge.intefaces.json.response.GetWishlistResponse;
import com.challenge.services.WishlistService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/wishlist")
@Validated
@AllArgsConstructor
public class WishlistController implements BaseController {

  private final WishlistService wishlistService;

  @PostMapping
  public ResponseEntity<Void> postWishlist(@Valid @RequestBody PostWishlistRequest wishlistPost) {
    WishlistDomain wishlistDomain = wishlistService.postWishlist(wishlistPost);
    return created(wishlistDomain.getId().toString());
  }

  @GetMapping(path = "/{idWish}")
  public ResponseEntity<GetWishlistResponse> getWishlistById(@PathVariable("idWish") ObjectId idWishlist) {
    return ok(this.wishlistService.getWishlistById(idWishlist));
  }

  @GetMapping
  public ResponseEntity<List<GetWishlistResponse>> getAllWishlist(
      @RequestParam("_offset") @PositiveOrZero @NotNull Integer offset,
      @RequestParam("_limit") @Positive @NotNull Integer limit) {
    return getResult(this.wishlistService.getAllWishlist(limit, offset));
  }

  @DeleteMapping(path = "/{idWish}")
  public ResponseEntity<Void> deleteTemplate(@PathVariable("idWish") ObjectId idWish) {
    this.wishlistService.deleteWishlist(idWish);
    return noContent();
  }
}
