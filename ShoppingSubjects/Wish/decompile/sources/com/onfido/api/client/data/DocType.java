package com.onfido.api.client.data;

public enum DocType {
    PASSPORT("passport"),
    DRIVING_LICENSE("driving_licence"),
    NATIONAL_ID_CARD("national_identity_card"),
    UNKNOWN("unknown");
    
    private final String id;

    private DocType(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
