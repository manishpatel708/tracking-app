package com.interview.tracking_app.controller;

import com.interview.tracking_app.model.NextTrackingNumberRequest;
import com.interview.tracking_app.model.TrackingNumber;
import com.interview.tracking_app.service.TrackingNumberService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/next-tracking-number")
@Validated
public class TrackingNumberController {

    private final TrackingNumberService trackingNumberService;

    public TrackingNumberController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }

    @GetMapping
    public ResponseEntity<TrackingNumber> getTrackingNumber(
            @RequestParam("origin_country_id") String originCountryId,
            @RequestParam("destination_country_id") String destinationCountryId,
            @RequestParam double weight,
            @RequestParam("created_at") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime createdAt,
            @RequestParam("customer_id") UUID customerId,
            @RequestParam("customer_name") String customerName,
            @RequestParam("customer_slug") String customerSlug
    ) throws ExecutionException, InterruptedException
    {
        NextTrackingNumberRequest request = NextTrackingNumberRequest
                .builder()
                .originCountryId(originCountryId)
                .destinationCountryId(destinationCountryId)
                .weight(weight)
                .createdAt(createdAt)
                .customerId(customerId)
                .customerName(customerName)
                .customerSlug(customerSlug)
                .build();
        TrackingNumber record = trackingNumberService.generateTrackingNumber(request).get();
        return ResponseEntity.ok(record);
    }
}
