package com.salesforce.marketingcloud.analytics;

import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import org.json.JSONObject;

public abstract class PiCartItem implements Parcelable {
    @NonNull
    public static PiCartItem create(@NonNull String str, int i, double d) {
        g gVar = new g(str, i, d, null);
        return gVar;
    }

    @NonNull
    public static PiCartItem create(@NonNull String str, int i, double d, String str2) {
        g gVar = new g(str, i, d, str2);
        return gVar;
    }

    public abstract JSONObject a();

    @NonNull
    public abstract String item();

    @NonNull
    public abstract double price();

    @NonNull
    public abstract int quantity();

    @Nullable
    public abstract String uniqueId();
}
