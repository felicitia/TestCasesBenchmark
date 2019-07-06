package com.stripe.model;

import java.util.Map;

public class EventData extends StripeObject {
    StripeObject object;
    Map<String, Object> previousAttributes;

    public void setPreviousAttributes(Map<String, Object> map) {
        this.previousAttributes = map;
    }

    public void setObject(StripeObject stripeObject) {
        this.object = stripeObject;
    }
}
