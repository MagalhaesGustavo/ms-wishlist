package com.challenge.services.impl;


import static com.challenge.utils.Constants.LIMIT_PRODUCTS;

import com.challenge.domain.WishlistDomain;
import com.challenge.exceptions.MessageError;
import com.challenge.exceptions.NotFoundException;
import com.challenge.exceptions.UnprocessableEntityException;
import com.challenge.intefaces.Messages;
import com.challenge.intefaces.json.request.PostWishlistRequest;
import com.challenge.intefaces.json.response.GetWishlistResponse;
import com.challenge.repository.WishlistRepository;
import com.challenge.services.WishlistService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class WishlistServiceImpl implements WishlistService {

  private final WishlistRepository wishlistRepository;
  private final MessageError messageError;

  @Override
  public WishlistDomain postWishlist(PostWishlistRequest postWishlistRequest) {

    validateTotalDomain();
    return wishlistRepository.save(postWishlistRequest.toPostWishlistDomain());
  }

  @Override
  @Transactional(readOnly = true)
  public GetWishlistResponse getWishlistById(ObjectId idWishlist) {
    return GetWishlistResponse.valueOf(getWishlistDomainById(idWishlist));
  }

  @Override
  @Transactional(readOnly = true)
  public Page<GetWishlistResponse> getAllWishlist(Integer limit, Integer offSet) {

    Page<WishlistDomain> wishlistDomains = this.wishlistRepository.findAll(PageRequest.of(offSet, limit));
    return wishlistDomains.map(WishlistDomain::toWishlistResponse);
  }

  @Override
  @Transactional
  public void deleteWishlist(ObjectId idWish) {
    this.wishlistRepository.delete(getWishlistDomainById(idWish));
  }

  private WishlistDomain getWishlistDomainById(ObjectId idWish) {
    return this.wishlistRepository.findById(idWish)
        .orElseThrow(() -> new NotFoundException(this.messageError.create(Messages.WISH_LIST_ID_NOT_FOUND)));
  }

  private void validateTotalDomain() {

    if (this.wishlistRepository.findAll().size() >= LIMIT_PRODUCTS) {
      throw new UnprocessableEntityException(this.messageError.create(Messages.PRODUCT_CANNOT_BE_ADD));
    }
  }
}
