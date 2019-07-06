package com.facebook.places.model;

public class CurrentPlaceRequestParams {

    public enum ConfidenceLevel {
        LOW,
        MEDIUM,
        HIGH
    }

    public enum ScanMode {
        HIGH_ACCURACY,
        LOW_LATENCY
    }
}
