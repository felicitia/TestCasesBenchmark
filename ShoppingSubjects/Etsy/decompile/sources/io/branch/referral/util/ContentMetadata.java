package io.branch.referral.util;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.a.a.a.a.a.a;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.h;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContentMetadata implements Parcelable {
    public static final Creator CREATOR = new Creator() {
        /* renamed from: a */
        public ContentMetadata createFromParcel(Parcel parcel) {
            return new ContentMetadata(parcel);
        }

        /* renamed from: a */
        public ContentMetadata[] newArray(int i) {
            return new ContentMetadata[i];
        }
    };
    public String addressCity;
    public String addressCountry;
    public String addressPostalCode;
    public String addressRegion;
    public String addressStreet;
    public CONDITION condition;
    BranchContentSchema contentSchema;
    public CurrencyType currencyType;
    private final HashMap<String, String> customMetadata;
    private final ArrayList<String> imageCaptions;
    public Double latitude;
    public Double longitude;
    public Double price;
    public String productBrand;
    public ProductCategory productCategory;
    public String productName;
    public String productVariant;
    public Double quantity;
    public Double rating;
    public Double ratingAverage;
    public Integer ratingCount;
    public Double ratingMax;
    public String sku;

    public enum CONDITION {
        OTHER,
        NEW,
        GOOD,
        FAIR,
        POOR,
        USED,
        REFURBISHED,
        EXCELLENT;

        public static CONDITION getValue(String str) {
            CONDITION[] values;
            if (!TextUtils.isEmpty(str)) {
                for (CONDITION condition : values()) {
                    if (condition.name().equalsIgnoreCase(str)) {
                        return condition;
                    }
                }
            }
            return null;
        }
    }

    public int describeContents() {
        return 0;
    }

    public ContentMetadata() {
        this.imageCaptions = new ArrayList<>();
        this.customMetadata = new HashMap<>();
    }

    public ContentMetadata addImageCaptions(String... strArr) {
        Collections.addAll(this.imageCaptions, strArr);
        return this;
    }

    public ContentMetadata addCustomMetadata(String str, String str2) {
        this.customMetadata.put(str, str2);
        return this;
    }

    public ContentMetadata setContentSchema(BranchContentSchema branchContentSchema) {
        this.contentSchema = branchContentSchema;
        return this;
    }

    public ContentMetadata setQuantity(Double d) {
        this.quantity = d;
        return this;
    }

    public ContentMetadata setAddress(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        this.addressStreet = str;
        this.addressCity = str2;
        this.addressRegion = str3;
        this.addressCountry = str4;
        this.addressPostalCode = str5;
        return this;
    }

    public ContentMetadata setLocation(@Nullable Double d, @Nullable Double d2) {
        this.latitude = d;
        this.longitude = d2;
        return this;
    }

    public ContentMetadata setRating(@Nullable Double d, @Nullable Double d2, @Nullable Double d3, @Nullable Integer num) {
        this.rating = d;
        this.ratingAverage = d2;
        this.ratingMax = d3;
        this.ratingCount = num;
        return this;
    }

    public ContentMetadata setRating(@Nullable Double d, @Nullable Double d2, @Nullable Integer num) {
        this.ratingAverage = d;
        this.ratingMax = d2;
        this.ratingCount = num;
        return this;
    }

    public ContentMetadata setPrice(Double d, @Nullable CurrencyType currencyType2) {
        this.price = d;
        this.currencyType = currencyType2;
        return this;
    }

    public ContentMetadata setProductBrand(String str) {
        this.productBrand = str;
        return this;
    }

    public ContentMetadata setProductCategory(ProductCategory productCategory2) {
        this.productCategory = productCategory2;
        return this;
    }

    public ContentMetadata setProductCondition(CONDITION condition2) {
        this.condition = condition2;
        return this;
    }

    public ContentMetadata setProductName(String str) {
        this.productName = str;
        return this;
    }

    public ContentMetadata setProductVariant(String str) {
        this.productVariant = str;
        return this;
    }

    public ContentMetadata setSku(String str) {
        this.sku = str;
        return this;
    }

    public ArrayList<String> getImageCaptions() {
        return this.imageCaptions;
    }

    public HashMap<String, String> getCustomMetadata() {
        return this.customMetadata;
    }

    public JSONObject convertToJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.contentSchema != null) {
                jSONObject.put(Jsonkey.ContentSchema.getKey(), this.contentSchema.name());
            }
            if (this.quantity != null) {
                jSONObject.put(Jsonkey.Quantity.getKey(), this.quantity);
            }
            if (this.price != null) {
                jSONObject.put(Jsonkey.Price.getKey(), this.price);
            }
            if (this.currencyType != null) {
                jSONObject.put(Jsonkey.PriceCurrency.getKey(), this.currencyType.toString());
            }
            if (!TextUtils.isEmpty(this.sku)) {
                jSONObject.put(Jsonkey.SKU.getKey(), this.sku);
            }
            if (!TextUtils.isEmpty(this.productName)) {
                jSONObject.put(Jsonkey.ProductName.getKey(), this.productName);
            }
            if (!TextUtils.isEmpty(this.productBrand)) {
                jSONObject.put(Jsonkey.ProductBrand.getKey(), this.productBrand);
            }
            if (this.productCategory != null) {
                jSONObject.put(Jsonkey.ProductCategory.getKey(), this.productCategory.getName());
            }
            if (this.condition != null) {
                jSONObject.put(Jsonkey.Condition.getKey(), this.condition.name());
            }
            if (!TextUtils.isEmpty(this.productVariant)) {
                jSONObject.put(Jsonkey.ProductVariant.getKey(), this.productVariant);
            }
            if (this.rating != null) {
                jSONObject.put(Jsonkey.Rating.getKey(), this.rating);
            }
            if (this.ratingAverage != null) {
                jSONObject.put(Jsonkey.RatingAverage.getKey(), this.ratingAverage);
            }
            if (this.ratingCount != null) {
                jSONObject.put(Jsonkey.RatingCount.getKey(), this.ratingCount);
            }
            if (this.ratingMax != null) {
                jSONObject.put(Jsonkey.RatingMax.getKey(), this.ratingMax);
            }
            if (!TextUtils.isEmpty(this.addressStreet)) {
                jSONObject.put(Jsonkey.AddressStreet.getKey(), this.addressStreet);
            }
            if (!TextUtils.isEmpty(this.addressCity)) {
                jSONObject.put(Jsonkey.AddressCity.getKey(), this.addressCity);
            }
            if (!TextUtils.isEmpty(this.addressRegion)) {
                jSONObject.put(Jsonkey.AddressRegion.getKey(), this.addressRegion);
            }
            if (!TextUtils.isEmpty(this.addressCountry)) {
                jSONObject.put(Jsonkey.AddressCountry.getKey(), this.addressCountry);
            }
            if (!TextUtils.isEmpty(this.addressPostalCode)) {
                jSONObject.put(Jsonkey.AddressPostalCode.getKey(), this.addressPostalCode);
            }
            if (this.latitude != null) {
                jSONObject.put(Jsonkey.Latitude.getKey(), this.latitude);
            }
            if (this.longitude != null) {
                jSONObject.put(Jsonkey.Longitude.getKey(), this.longitude);
            }
            if (this.imageCaptions.size() > 0) {
                JSONArray jSONArray = new JSONArray();
                jSONObject.put(Jsonkey.ImageCaptions.getKey(), jSONArray);
                Iterator it = this.imageCaptions.iterator();
                while (it.hasNext()) {
                    jSONArray.put((String) it.next());
                }
            }
            if (this.customMetadata.size() > 0) {
                for (String str : this.customMetadata.keySet()) {
                    jSONObject.put(str, this.customMetadata.get(str));
                }
            }
        } catch (JSONException e) {
            a.a(e);
        }
        return jSONObject;
    }

    public static ContentMetadata createFromJson(h.a aVar) {
        ContentMetadata contentMetadata = new ContentMetadata();
        contentMetadata.contentSchema = BranchContentSchema.getValue(aVar.a(Jsonkey.ContentSchema.getKey()));
        contentMetadata.quantity = aVar.a(Jsonkey.Quantity.getKey(), (Double) null);
        contentMetadata.price = aVar.a(Jsonkey.Price.getKey(), (Double) null);
        contentMetadata.currencyType = CurrencyType.getValue(aVar.a(Jsonkey.PriceCurrency.getKey()));
        contentMetadata.sku = aVar.a(Jsonkey.SKU.getKey());
        contentMetadata.productName = aVar.a(Jsonkey.ProductName.getKey());
        contentMetadata.productBrand = aVar.a(Jsonkey.ProductBrand.getKey());
        contentMetadata.productCategory = ProductCategory.getValue(aVar.a(Jsonkey.ProductCategory.getKey()));
        contentMetadata.condition = CONDITION.getValue(aVar.a(Jsonkey.Condition.getKey()));
        contentMetadata.productVariant = aVar.a(Jsonkey.ProductVariant.getKey());
        contentMetadata.rating = aVar.a(Jsonkey.Rating.getKey(), (Double) null);
        contentMetadata.ratingAverage = aVar.a(Jsonkey.RatingAverage.getKey(), (Double) null);
        contentMetadata.ratingCount = aVar.a(Jsonkey.RatingCount.getKey(), (Integer) null);
        contentMetadata.ratingMax = aVar.a(Jsonkey.RatingMax.getKey(), (Double) null);
        contentMetadata.addressStreet = aVar.a(Jsonkey.AddressStreet.getKey());
        contentMetadata.addressCity = aVar.a(Jsonkey.AddressCity.getKey());
        contentMetadata.addressRegion = aVar.a(Jsonkey.AddressRegion.getKey());
        contentMetadata.addressCountry = aVar.a(Jsonkey.AddressCountry.getKey());
        contentMetadata.addressPostalCode = aVar.a(Jsonkey.AddressPostalCode.getKey());
        contentMetadata.latitude = aVar.a(Jsonkey.Latitude.getKey(), (Double) null);
        contentMetadata.longitude = aVar.a(Jsonkey.Longitude.getKey(), (Double) null);
        JSONArray d = aVar.d(Jsonkey.ImageCaptions.getKey());
        if (d != null) {
            for (int i = 0; i < d.length(); i++) {
                contentMetadata.imageCaptions.add(d.optString(i));
            }
        }
        try {
            JSONObject a = aVar.a();
            Iterator keys = a.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                contentMetadata.customMetadata.put(str, a.optString(str));
            }
        } catch (Exception e) {
            a.a(e);
        }
        return contentMetadata;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.contentSchema != null ? this.contentSchema.name() : "");
        parcel.writeSerializable(this.quantity);
        parcel.writeSerializable(this.price);
        parcel.writeString(this.currencyType != null ? this.currencyType.name() : "");
        parcel.writeString(this.sku);
        parcel.writeString(this.productName);
        parcel.writeString(this.productBrand);
        parcel.writeString(this.productCategory != null ? this.productCategory.getName() : "");
        parcel.writeString(this.condition != null ? this.condition.name() : "");
        parcel.writeString(this.productVariant);
        parcel.writeSerializable(this.rating);
        parcel.writeSerializable(this.ratingAverage);
        parcel.writeSerializable(this.ratingCount);
        parcel.writeSerializable(this.ratingMax);
        parcel.writeString(this.addressStreet);
        parcel.writeString(this.addressCity);
        parcel.writeString(this.addressRegion);
        parcel.writeString(this.addressCountry);
        parcel.writeString(this.addressPostalCode);
        parcel.writeSerializable(this.latitude);
        parcel.writeSerializable(this.longitude);
        parcel.writeSerializable(this.imageCaptions);
        parcel.writeSerializable(this.customMetadata);
    }

    private ContentMetadata(Parcel parcel) {
        this();
        this.contentSchema = BranchContentSchema.getValue(parcel.readString());
        this.quantity = (Double) parcel.readSerializable();
        this.price = (Double) parcel.readSerializable();
        this.currencyType = CurrencyType.getValue(parcel.readString());
        this.sku = parcel.readString();
        this.productName = parcel.readString();
        this.productBrand = parcel.readString();
        this.productCategory = ProductCategory.getValue(parcel.readString());
        this.condition = CONDITION.getValue(parcel.readString());
        this.productVariant = parcel.readString();
        this.rating = (Double) parcel.readSerializable();
        this.ratingAverage = (Double) parcel.readSerializable();
        this.ratingCount = (Integer) parcel.readSerializable();
        this.ratingMax = (Double) parcel.readSerializable();
        this.addressStreet = parcel.readString();
        this.addressCity = parcel.readString();
        this.addressRegion = parcel.readString();
        this.addressCountry = parcel.readString();
        this.addressPostalCode = parcel.readString();
        this.latitude = (Double) parcel.readSerializable();
        this.longitude = (Double) parcel.readSerializable();
        this.imageCaptions.addAll((ArrayList) parcel.readSerializable());
        this.customMetadata.putAll((HashMap) parcel.readSerializable());
    }
}
