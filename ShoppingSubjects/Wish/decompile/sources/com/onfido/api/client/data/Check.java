package com.onfido.api.client.data;

import java.io.Serializable;

public class Check implements Serializable {
    private String id;
    private Result result;
    private String status;
    private Type type;

    public static final class Builder {
        /* access modifiers changed from: private */
        public String id;
        /* access modifiers changed from: private */
        public Result result;
        /* access modifiers changed from: private */
        public String status;
        /* access modifiers changed from: private */
        public Type type;

        private Builder() {
        }

        public Check build() {
            return new Check(this);
        }
    }

    public enum Type {
        STANDARD("standard"),
        EXPRESS("express");
        
        private String name;

        private Type(String str) {
            this.name = str;
        }

        public String toString() {
            return this.name;
        }
    }

    private Check(Builder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.status = builder.status;
        this.result = builder.result;
    }

    public static Builder builder() {
        return new Builder();
    }
}
