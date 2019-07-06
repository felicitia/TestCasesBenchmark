package com.contextlogic.wish.api;

import com.contextlogic.wish.api.model.BuyerGuaranteeInfo;
import com.contextlogic.wish.api.model.PageItemHolder;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

public final class AutoValueGson_WishTypeAdapterFactory extends WishTypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
        Class rawType = typeToken.getRawType();
        if (BuyerGuaranteeInfo.class.isAssignableFrom(rawType)) {
            return BuyerGuaranteeInfo.typeAdapter(gson);
        }
        if (PageItemHolder.class.isAssignableFrom(rawType)) {
            return PageItemHolder.typeAdapter(gson);
        }
        return null;
    }
}
