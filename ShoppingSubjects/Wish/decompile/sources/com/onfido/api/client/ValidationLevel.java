package com.onfido.api.client;

public enum ValidationLevel {
    ERROR("error"),
    WARNING("warn");
    
    private String id;

    private ValidationLevel(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
