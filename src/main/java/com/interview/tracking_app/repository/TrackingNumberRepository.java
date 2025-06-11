package com.interview.tracking_app.repository;

import com.interview.tracking_app.model.TrackingNumber;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TrackingNumberRepository extends MongoRepository<TrackingNumber, String> {
    //    boolean existsByTrackingNumber(String trackingNumber);
    Optional<TrackingNumber> findByTrackingNumber(String trackingNumber);
}
