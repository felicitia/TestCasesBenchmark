package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import com.contextlogic.wish.api.model.C$AutoValue_PageItemHolder.GsonTypeAdapter;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

@AutoValue
@SuppressLint({"NullableNonNullAnnotationRequired"})
public abstract class PageItemHolder implements Parcelable {
    @SerializedName("page_item_action")
    public abstract String getAction();

    @SerializedName("page_item_body")
    public abstract String getBody();

    @SerializedName("page_item_img")
    public abstract String getImgUrl();

    @SerializedName("page_item_title")
    public abstract String getTitle();

    public static TypeAdapter<PageItemHolder> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
