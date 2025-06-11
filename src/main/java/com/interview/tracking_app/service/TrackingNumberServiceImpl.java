package com.interview.tracking_app.service;

import com.interview.tracking_app.exception.TrackingNumberGenerationException;
import com.interview.tracking_app.model.NextTrackingNumberRequest;
import com.interview.tracking_app.model.TrackingNumber;
import com.interview.tracking_app.repository.TrackingNumberRepository;
import com.mongodb.DuplicateKeyException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j
public class TrackingNumberServiceImpl implements TrackingNumberService
{
    private static final int MAX_LENGTH = 16;
    private static final int MAX_ATTEMPTS = 10;

    @Autowired
    private TrackingNumberRepository repository;

    @Async
    @Override
    @Transactional
    public CompletableFuture<TrackingNumber> generateTrackingNumber(NextTrackingNumberRequest request) {
        int attempts = 0;
        while (attempts < MAX_ATTEMPTS) {
            String trackingNumber = createTrackingNumber(request);
            TrackingNumber record = new TrackingNumber();
            record.setId(UUID.randomUUID().toString());
            record.setTrackingNumber(trackingNumber);
            record.setCreatedAt(ZonedDateTime.now());
            try {
                TrackingNumber saved = repository.save(record);
                return CompletableFuture.completedFuture(saved);
            } catch (DuplicateKeyException e) {
                attempts++;
                log.warn("Duplicate tracking number '{}' detected on attempt {} for customer '{}'. Retrying...", trackingNumber, attempts, request.getCustomerId());
                try {
                    Thread.sleep(10 * attempts); // Exponential backoff
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new TrackingNumberGenerationException("Thread interrupted during tracking number generation", request, ie);
                }
            }
        }
        throw new TrackingNumberGenerationException("Failed to generate unique tracking number after " + MAX_ATTEMPTS + " attempts", request);
    }

    private String createTrackingNumber(NextTrackingNumberRequest request) {
        String base = request.getOriginCountryId() +
                request.getDestinationCountryId() +
                String.format("%03d", (int) (request.getWeight() * 100)) +
                request.getCustomerSlug().replace("-", "").substring(0, Math.min(4, request.getCustomerSlug().length())) +
                Integer.toHexString(ThreadLocalRandom.current().nextInt(0, 65536)).toUpperCase();
        return base.substring(0, Math.min(base.length(), MAX_LENGTH)).toUpperCase();
    }
}
