package com.interview.tracking_app.model;

import io.swagger.v3.oas.annotations.Parameter;
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

    @Parameter(
            description = "Origin country code (ISO 3166-1 alpha-2)",
            example = "US",
            required = true
    )
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$")
    String originCountryId;

    @Parameter(description = "Destination country code", example = "CA")
    @NotBlank
    @Pattern(regexp = "^[A-Z]{2}$")
    String destinationCountryId;

    @Parameter(description = "Weight in kilograms (up to 3 decimal places)", example = "2.345")
    @NotNull
    @DecimalMin("0.001")
    @Digits(integer = 10, fraction = 3)
    Double weight;

    @Parameter(description = "Creation timestamp (RFC 3339)", example = "2025-06-12T10:30:00+05:30")
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    ZonedDateTime createdAt;

    @Parameter(description = "Customer UUID", example = "de619854-b59b-425e-9db4-943979e1bd49")
    @NotNull
    UUID customerId;

    @Parameter(description = "Customer name", example = "Fast Logistics")
    @NotBlank
    String customerName;

    @Parameter(description = "Customer slug (kebab-case)", example = "fast-logistics")
    @NotBlank
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$")
    String customerSlug;
}
