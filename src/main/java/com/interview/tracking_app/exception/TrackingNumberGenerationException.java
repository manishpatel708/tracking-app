package com.interview.tracking_app.exception;

public class TrackingNumberGenerationException extends RuntimeException {

    private final Object requestDetails;

    public TrackingNumberGenerationException(String message, Object requestDetails) {
        super(message);
        this.requestDetails = requestDetails;
    }

    public TrackingNumberGenerationException(String message, Object requestDetails, Throwable cause) {
        super(message, cause);
        this.requestDetails = requestDetails;
    }

    public Object getRequestDetails() {
        return requestDetails;
    }

    @Override
    public String toString() {
        return "TrackingNumberGenerationException{" +
                "message=" + getMessage() +
                ", requestDetails=" + requestDetails +
                '}';
    }
}
