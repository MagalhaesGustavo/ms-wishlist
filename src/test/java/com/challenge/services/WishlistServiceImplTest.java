package com.challenge.services;

import static com.challenge.builders.PostWishlistRequestBuilder.createPostWishlistRequestSuccess;
import static com.challenge.builders.WishlistDomainBuilder.createWishlistDomainSuccess;
import static com.challenge.utils.Constants.LIMIT_PRODUCTS;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.challenge.domain.WishlistDomain;
import com.challenge.exceptions.MessageError;
import com.challenge.exceptions.UnprocessableEntityException;
import com.challenge.intefaces.json.request.PostWishlistRequest;
import com.challenge.intefaces.json.response.GetWishlistResponse;
import com.challenge.repository.WishlistRepository;
import com.challenge.services.impl.WishlistServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;


@ExtendWith(MockitoExtension.class)
class WishlistServiceImplTest {

  @Mock
  private WishlistRepository wishlistRepository;

  @Mock
  private MessageError messageError;

  private WishlistServiceImpl wishlistService;

  @BeforeEach
  void setUp() {
    this.wishlistService = new WishlistServiceImpl(wishlistRepository, messageError);
  }

  @Test
  void shouldPostWishlistSuccessfully() {
    PostWishlistRequest postWishlistRequestSuccess = createPostWishlistRequestSuccess();
    WishlistDomain wishlistDomainSuccess = createWishlistDomainSuccess();

    when(wishlistRepository.save(any())).thenReturn(wishlistDomainSuccess);

    WishlistDomain wishlistDomain = this.wishlistService.postWishlist(postWishlistRequestSuccess);
    assertEquals(wishlistDomain.getProductId(), postWishlistRequestSuccess.getProductId());
    assertEquals(wishlistDomain.getName(), postWishlistRequestSuccess.getNome());
  }

  @Test
  void shouldPostWishlistWithException() {

    PostWishlistRequest postWishlistRequestSuccess = createPostWishlistRequestSuccess();
    List<WishlistDomain> wishlistDomains = createMockedList();

    when(this.wishlistRepository.findAll()).thenReturn(wishlistDomains);

    assertThatThrownBy(() -> this.wishlistService.postWishlist(postWishlistRequestSuccess)).isInstanceOf(UnprocessableEntityException.class);
  }

  @Test
  void shouldGetAllWishlistSuccessfully() {

    Page<WishlistDomain> page = new PageImpl<>(List.of(createWishlistDomainSuccess()));

    when(this.wishlistRepository.findAll(PageRequest.of(0, 10))).thenReturn(page);

    Page<GetWishlistResponse> allWishlist = this.wishlistService.getAllWishlist(10, 0);

    assertNotNull(allWishlist);
  }

  @Test
  void shouldGetWishlistByIdSuccessfully() {

    WishlistDomain wishlistDomainSuccess = createWishlistDomainSuccess();

    when(this.wishlistRepository.findById(any())).thenReturn(Optional.of(wishlistDomainSuccess));

    GetWishlistResponse getWishlistResponse = this.wishlistService.getWishlistById(new ObjectId());

    assertEquals(wishlistDomainSuccess.getProductId(), getWishlistResponse.getProductId());
    assertEquals(wishlistDomainSuccess.getName(), getWishlistResponse.getNome());
  }

  @Test
  void shouldDeleteSuccessfully() {
    WishlistDomain wishlistDomainSuccess = createWishlistDomainSuccess();

    when(this.wishlistRepository.findById(any())).thenReturn(Optional.of(wishlistDomainSuccess));

    this.wishlistService.deleteWishlist(new ObjectId());
  }

  private List<WishlistDomain> createMockedList() {

    List<WishlistDomain> wishlistDomains = new ArrayList<>();

    while (wishlistDomains.size() < LIMIT_PRODUCTS) {
      wishlistDomains.add(createWishlistDomainSuccess());
    }
    return wishlistDomains;
  }
}
