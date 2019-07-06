package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishUgcRatableProduct extends BaseModel implements Parcelable {
    public static final Creator<WishUgcRatableProduct> CREATOR = new Creator<WishUgcRatableProduct>() {
        public WishUgcRatableProduct createFromParcel(Parcel parcel) {
            return new WishUgcRatableProduct(parcel);
        }

        public WishUgcRatableProduct[] newArray(int i) {
            return new WishUgcRatableProduct[i];
        }
    };
    private String mActionDeepLink;
    private String mActionString;
    private String mDismissString;
    private String mProductDescription;
    private String mProductId;
    private String mProductImageUrl;

    public int describeContents() {
        return 0;
    }

    public WishUgcRatableProduct(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishUgcRatableProduct(Parcel parcel) {
        this.mProductId = parcel.readString();
        this.mProductImageUrl = parcel.readString();
        this.mDismissString = parcel.readString();
        this.mActionString = parcel.readString();
        this.mActionDeepLink = parcel.readString();
        this.mProductDescription = parcel.readString();
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mProductId = JsonUtil.optString(jSONObject, "product_id");
        this.mProductImageUrl = JsonUtil.optString(jSONObject, "product_image_url");
        this.mDismissString = JsonUtil.optString(jSONObject, "dismiss_string");
        this.mActionString = JsonUtil.optString(jSONObject, "action_string");
        this.mActionDeepLink = JsonUtil.optString(jSONObject, "action");
        this.mProductDescription = JsonUtil.optString(jSONObject, "product_description");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mProductImageUrl);
        parcel.writeString(this.mDismissString);
        parcel.writeString(this.mActionString);
        parcel.writeString(this.mActionDeepLink);
        parcel.writeString(this.mProductDescription);
    }

    public String getProductId() {
        return this.mProductId;
    }

    public String getProductImageUrl() {
        return this.mProductImageUrl;
    }

    public String getDismissString() {
        return this.mDismissString;
    }

    public String getActionString() {
        return this.mActionString;
    }

    public String getActionDeepLink() {
        return this.mActionDeepLink;
    }

    public String getProductDescription() {
        return this.mProductDescription;
    }
}
