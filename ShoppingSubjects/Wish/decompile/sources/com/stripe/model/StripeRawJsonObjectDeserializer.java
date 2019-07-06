package com.stripe.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class StripeRawJsonObjectDeserializer implements JsonDeserializer<StripeRawJsonObject> {
    public StripeRawJsonObject deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        StripeRawJsonObject stripeRawJsonObject = new StripeRawJsonObject();
        stripeRawJsonObject.json = jsonElement.getAsJsonObject();
        return stripeRawJsonObject;
    }
}
