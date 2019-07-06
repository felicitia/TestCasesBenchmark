package com.kount.api;

enum SoftError {
    SERVICE_UNAVAILABLE("not_available"),
    UNEXPECTED("unexpected"),
    SKIPPED("skipped"),
    PERMISSION_DENIED("permission_denied"),
    LOCATION_COLLECTOR_UNAVAILABLE("location_collector_unavailable");
    
    private final String name;

    private SoftError(String str) {
        this.name = str;
    }

    public String toString() {
        return this.name;
    }
}
