package com.stripe.net;

import java.util.List;
import java.util.Map;

public class StripeResponse {
    String responseBody;
    int responseCode;
    Map<String, List<String>> responseHeaders;

    public StripeResponse(int i, String str) {
        this.responseCode = i;
        this.responseBody = str;
        this.responseHeaders = null;
    }

    public StripeResponse(int i, String str, Map<String, List<String>> map) {
        this.responseCode = i;
        this.responseBody = str;
        this.responseHeaders = map;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this.responseHeaders;
    }
}
