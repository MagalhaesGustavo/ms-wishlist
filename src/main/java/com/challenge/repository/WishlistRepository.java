package com.challenge.repository;

import com.challenge.domain.WishlistDomain;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends MongoRepository<WishlistDomain, ObjectId> {

}
