package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import com.contextlogic.wish.api.model.C$AutoValue_BuyerGuaranteeInfo.GsonTypeAdapter;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import java.util.List;

@AutoValue
@SuppressLint({"NullableNonNullAnnotationRequired"})
public abstract class BuyerGuaranteeInfo implements Parcelable {
    @SerializedName("page_items")
    public abstract List<PageItemHolder> getPageItems();

    @SerializedName("page_subtitle")
    public abstract String getPageSubtitle();

    @SerializedName("page_title")
    public abstract String getPageTitle();

    @SerializedName("section_body")
    public abstract String getSectionBody();

    @SerializedName("section_subtitle")
    public abstract String getSectionSubtitle();

    @SerializedName("section_title")
    public abstract String getSectionTitle();

    public static TypeAdapter<BuyerGuaranteeInfo> typeAdapter(Gson gson) {
        return new GsonTypeAdapter(gson);
    }
}
