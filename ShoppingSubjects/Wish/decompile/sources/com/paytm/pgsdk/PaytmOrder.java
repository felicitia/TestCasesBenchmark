package com.paytm.pgsdk;

import java.util.Map;

public class PaytmOrder {
    private Map<String, String> requestParamMap;

    public PaytmOrder(Map<String, String> map) {
        this.requestParamMap = map;
    }

    public Map<String, String> getRequestParamMap() {
        return this.requestParamMap;
    }
}
