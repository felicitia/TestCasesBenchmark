package com.stripe.model;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class ChargeRefundCollectionDeserializer implements JsonDeserializer<ChargeRefundCollection> {
    public ChargeRefundCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson create = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        if (!jsonElement.isJsonArray()) {
            return (ChargeRefundCollection) create.fromJson(jsonElement, type);
        }
        List list = (List) create.fromJson(jsonElement, new TypeToken<List<Refund>>() {
        }.getType());
        ChargeRefundCollection chargeRefundCollection = new ChargeRefundCollection();
        chargeRefundCollection.setData(list);
        chargeRefundCollection.setHasMore(Boolean.valueOf(false));
        chargeRefundCollection.setTotalCount(Integer.valueOf(list.size()));
        return chargeRefundCollection;
    }
}
