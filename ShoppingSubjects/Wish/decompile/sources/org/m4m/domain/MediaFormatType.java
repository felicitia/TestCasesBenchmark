package org.m4m.domain;

public enum MediaFormatType {
    VIDEO("video"),
    AUDIO("audio");
    
    private final String type;

    private MediaFormatType(String str) {
        this.type = str;
    }

    public String toString() {
        return this.type;
    }
}
