package com.interview.tracking_app.service;

import com.interview.tracking_app.model.NextTrackingNumberRequest;
import com.interview.tracking_app.model.TrackingNumber;

import java.util.concurrent.CompletableFuture;

public interface TrackingNumberService {
    CompletableFuture<TrackingNumber> generateTrackingNumber(NextTrackingNumberRequest request);
}