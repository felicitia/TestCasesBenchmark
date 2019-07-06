package com.stripe.net;

import com.stripe.Stripe;

public class RequestOptions {
    private final String apiKey;
    private final String idempotencyKey;
    private final String stripeAccount;
    private final String stripeVersion;

    public static class InvalidRequestOptionsException extends RuntimeException {
        public InvalidRequestOptionsException(String str) {
            super(str);
        }
    }

    public static final class RequestOptionsBuilder {
        private String apiKey = Stripe.apiKey;
        private String idempotencyKey;
        private String stripeAccount;
        private String stripeVersion = Stripe.apiVersion;

        public RequestOptionsBuilder setApiKey(String str) {
            this.apiKey = RequestOptions.normalizeApiKey(str);
            return this;
        }

        public RequestOptions build() {
            RequestOptions requestOptions = new RequestOptions(RequestOptions.normalizeApiKey(this.apiKey), RequestOptions.normalizeStripeVersion(this.stripeVersion), RequestOptions.normalizeIdempotencyKey(this.idempotencyKey), RequestOptions.normalizeStripeAccount(this.stripeAccount));
            return requestOptions;
        }
    }

    public static RequestOptions getDefault() {
        return new RequestOptions(Stripe.apiKey, Stripe.apiVersion, null, null);
    }

    private RequestOptions(String str, String str2, String str3, String str4) {
        this.apiKey = str;
        this.stripeVersion = str2;
        this.idempotencyKey = str3;
        this.stripeAccount = str4;
    }

    public String getApiKey() {
        return this.apiKey;
    }

    public String getStripeVersion() {
        return this.stripeVersion;
    }

    public String getIdempotencyKey() {
        return this.idempotencyKey;
    }

    public String getStripeAccount() {
        return this.stripeAccount;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RequestOptions requestOptions = (RequestOptions) obj;
        if (this.apiKey == null ? requestOptions.apiKey != null : !this.apiKey.equals(requestOptions.apiKey)) {
            return false;
        }
        if (this.idempotencyKey == null ? requestOptions.idempotencyKey == null : this.idempotencyKey.equals(requestOptions.idempotencyKey)) {
            return this.stripeVersion == null ? requestOptions.stripeVersion == null : this.stripeVersion.equals(requestOptions.stripeVersion);
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.apiKey != null ? this.apiKey.hashCode() : 0) * 31) + (this.stripeVersion != null ? this.stripeVersion.hashCode() : 0)) * 31;
        if (this.idempotencyKey != null) {
            i = this.idempotencyKey.hashCode();
        }
        return hashCode + i;
    }

    public static RequestOptionsBuilder builder() {
        return new RequestOptionsBuilder();
    }

    /* access modifiers changed from: private */
    public static String normalizeApiKey(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (!trim.isEmpty()) {
            return trim;
        }
        throw new InvalidRequestOptionsException("Empty API key specified!");
    }

    /* access modifiers changed from: private */
    public static String normalizeStripeVersion(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (!trim.isEmpty()) {
            return trim;
        }
        throw new InvalidRequestOptionsException("Empty Stripe version specified!");
    }

    /* access modifiers changed from: private */
    public static String normalizeIdempotencyKey(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.isEmpty()) {
            throw new InvalidRequestOptionsException("Empty Idempotency Key Specified!");
        } else if (trim.length() <= 255) {
            return trim;
        } else {
            throw new InvalidRequestOptionsException(String.format("Idempotency Key length was %d, which is larger than the 255 character maximum!", new Object[]{Integer.valueOf(trim.length())}));
        }
    }

    /* access modifiers changed from: private */
    public static String normalizeStripeAccount(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (!trim.isEmpty()) {
            return trim;
        }
        throw new InvalidRequestOptionsException("Empty stripe account specified!");
    }
}
