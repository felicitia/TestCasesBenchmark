package com.contextlogic.wish.api;

import com.google.gson.TypeAdapterFactory;

public abstract class WishTypeAdapterFactory implements TypeAdapterFactory {
    public static TypeAdapterFactory create() {
        return new AutoValueGson_WishTypeAdapterFactory();
    }
}
