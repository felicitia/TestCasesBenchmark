package com.onfido.api.client;

public enum ValidationType {
    DOCUMENT("detect_document"),
    GLARE("detect_glare");
    
    private String id;

    private ValidationType(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }
}
