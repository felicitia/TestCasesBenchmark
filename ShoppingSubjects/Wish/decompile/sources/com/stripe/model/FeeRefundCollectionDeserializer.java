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

public class FeeRefundCollectionDeserializer implements JsonDeserializer<FeeRefundCollection> {
    public static final Type REFUND_LIST_TYPE = new TypeToken<List<Object>>() {
    }.getType();

    public FeeRefundCollection deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Gson create = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
        if (!jsonElement.isJsonArray()) {
            return (FeeRefundCollection) create.fromJson(jsonElement, type);
        }
        List list = (List) create.fromJson(jsonElement, REFUND_LIST_TYPE);
        FeeRefundCollection feeRefundCollection = new FeeRefundCollection();
        feeRefundCollection.setData(list);
        feeRefundCollection.setHasMore(Boolean.valueOf(false));
        feeRefundCollection.setTotalCount(Integer.valueOf(list.size()));
        return feeRefundCollection;
    }
}
