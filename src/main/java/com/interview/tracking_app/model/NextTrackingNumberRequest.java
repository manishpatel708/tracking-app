package com.interview.tracking_app.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NextTrackingNumberRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Origin country code must be ISO 3166-1 alpha-2")
    private String originCountryId;

    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$", message = "Destination country code must be ISO 3166-1 alpha-2")
    private String destinationCountryId;

    @NotNull
    @DecimalMin(value = "0.001", inclusive = true)
    @Digits(integer = 10, fraction = 3)
    private Double weight;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;

    @NotBlank
    @Pattern(regexp = "^[0-9a-fA-F\\-]{36}$", message = "Invalid UUID format")
    private UUID customerId;

    @NotBlank
    private String customerName;

    @NotBlank
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$", message = "Customer slug must be in kebab-case")
    private String customerSlug;

}
