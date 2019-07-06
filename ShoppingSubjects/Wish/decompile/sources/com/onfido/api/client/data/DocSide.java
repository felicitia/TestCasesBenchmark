package com.onfido.api.client.data;

public enum DocSide {
    FRONT("front"),
    BACK("back");
    
    private final String id;

    private DocSide(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
