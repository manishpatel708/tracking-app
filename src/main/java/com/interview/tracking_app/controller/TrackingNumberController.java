package com.interview.tracking_app.controller;

import com.interview.tracking_app.model.NextTrackingNumberRequest;
import com.interview.tracking_app.model.TrackingNumber;
import com.interview.tracking_app.service.TrackingNumberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/next-tracking-number")
public class TrackingNumberController {

    private final TrackingNumberService trackingNumberService;

    public TrackingNumberController(TrackingNumberService trackingNumberService) {
        this.trackingNumberService = trackingNumberService;
    }


    @GetMapping
    @Operation(
            summary = "Generate tracking number",
            description = "Creates a unique tracking number based on shipment details",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Tracking number generated successfully"),
                    @ApiResponse(responseCode = "400", description = "Invalid input parameters"),
                    @ApiResponse(responseCode = "500", description = "Internal server error")
            }
    )
    public ResponseEntity<TrackingNumber> getTrackingNumber(@Valid NextTrackingNumberRequest request
    ) throws ExecutionException, InterruptedException
    {
        TrackingNumber record = trackingNumberService.generateTrackingNumber(request).get();
        return ResponseEntity.ok(record);
    }
}
