package org.apache.http.entity.mime;

public class MinimalField {
    private final String name;
    private final String value;

    MinimalField(String str, String str2) {
        this.name = str;
        this.value = str2;
    }

    public String getName() {
        return this.name;
    }

    public String getBody() {
        return this.value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.name);
        sb.append(": ");
        sb.append(this.value);
        return sb.toString();
    }
}