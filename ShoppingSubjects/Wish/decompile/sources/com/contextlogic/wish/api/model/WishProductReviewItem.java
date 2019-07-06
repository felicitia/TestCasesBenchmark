package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishProductReviewItem extends BaseModel {
    public static final Creator<WishProductReviewItem> CREATOR = new Creator<WishProductReviewItem>() {
        public WishProductReviewItem createFromParcel(Parcel parcel) {
            return new WishProductReviewItem(parcel);
        }

        public WishProductReviewItem[] newArray(int i) {
            return new WishProductReviewItem[i];
        }
    };
    private String mColor;
    private boolean mHasRating;
    private String mMerchantDisplayName;
    private String mProductId;
    private WishImage mProductImage;
    private String mProductTitle;
    private Date mPurchaseTime;
    private String mSize;
    private String mTransactionId;

    public int describeContents() {
        return 0;
    }

    public WishProductReviewItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mProductId = JsonUtil.optString(jSONObject, "product_id");
        this.mTransactionId = JsonUtil.optString(jSONObject, "transaction_id");
        this.mProductTitle = JsonUtil.optString(jSONObject, "name");
        this.mMerchantDisplayName = JsonUtil.optString(jSONObject, "merchant_display_name");
        if (jSONObject.has("time")) {
            this.mPurchaseTime = DateUtil.parseIsoDate(JsonUtil.optString(jSONObject, "time"));
        }
        this.mSize = JsonUtil.optString(jSONObject, "size");
        this.mColor = JsonUtil.optString(jSONObject, "color");
        this.mHasRating = jSONObject.optBoolean("has_rating");
        this.mProductImage = new WishImage(jSONObject.optString("image", null));
    }

    public String getTransactionId() {
        return this.mTransactionId;
    }

    public String getProductId() {
        return this.mProductId;
    }

    public Date getPurchaseTime() {
        return this.mPurchaseTime;
    }

    public boolean hasRating() {
        return this.mHasRating;
    }

    public WishImage getProductImage() {
        return this.mProductImage;
    }

    public String getProductTitle() {
        return this.mProductTitle;
    }

    public String getMerchantDisplayName() {
        if (TextUtils.isEmpty(this.mMerchantDisplayName) || TextUtils.equals(this.mMerchantDisplayName, "null")) {
            return null;
        }
        return this.mMerchantDisplayName;
    }

    public String getSize() {
        return this.mSize;
    }

    public String getColor() {
        return this.mColor;
    }

    protected WishProductReviewItem(Parcel parcel) {
        this.mProductId = parcel.readString();
        this.mTransactionId = parcel.readString();
        this.mProductTitle = parcel.readString();
        this.mMerchantDisplayName = parcel.readString();
        this.mProductImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
        this.mPurchaseTime = new Date(parcel.readLong());
        this.mSize = parcel.readString();
        this.mColor = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mProductId);
        parcel.writeString(this.mTransactionId);
        parcel.writeString(this.mProductTitle);
        parcel.writeString(this.mMerchantDisplayName);
        parcel.writeParcelable(this.mProductImage, 0);
        parcel.writeLong(this.mPurchaseTime.getTime());
        parcel.writeString(this.mSize);
        parcel.writeString(this.mColor);
    }
}
