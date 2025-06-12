package com.interview.tracking_app.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "tracking_numbers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingNumber {

    @Id
    private String id;

    @Indexed(unique = true)
    private String trackingNumber;

    private Instant createdAt;
}

