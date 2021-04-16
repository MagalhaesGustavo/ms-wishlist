package com.challenge.services;

import com.challenge.domain.WishlistDomain;
import com.challenge.intefaces.json.request.PostWishlistRequest;
import com.challenge.intefaces.json.response.GetWishlistResponse;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;

public interface WishlistService {

  WishlistDomain postWishlist(PostWishlistRequest templatesPost);

  GetWishlistResponse getWishlistById(ObjectId idWishlist);

  Page<GetWishlistResponse> getAllWishlist(Integer limit, Integer offSet);

  void deleteWishlist(ObjectId idWishlist);
}
