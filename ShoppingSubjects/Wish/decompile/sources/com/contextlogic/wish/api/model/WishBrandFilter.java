package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;
import org.json.JSONObject;

public class WishBrandFilter extends BaseModel implements Parcelable {
    public static final Creator<WishBrandFilter> CREATOR = new Creator<WishBrandFilter>() {
        public WishBrandFilter createFromParcel(Parcel parcel) {
            return new WishBrandFilter(parcel);
        }

        public WishBrandFilter[] newArray(int i) {
            return new WishBrandFilter[i];
        }
    };
    private String mCredit;
    private boolean mIsMerchant;
    private ArrayList<WishImage> mPreviews;
    private String mPrice;
    private String mProducts;
    private String mQuery;
    private String mTag;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishBrandFilter(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mQuery = jSONObject.getString("brand");
        this.mTitle = this.mQuery;
        if (JsonUtil.hasValue(jSONObject, "price")) {
            this.mPrice = jSONObject.getString("price");
        }
        if (JsonUtil.hasValue(jSONObject, "tag")) {
            this.mTag = jSONObject.getString("tag");
        }
        if (JsonUtil.hasValue(jSONObject, "cids")) {
            this.mProducts = jSONObject.getString("cids");
        }
        if (JsonUtil.hasValue(jSONObject, "force_title")) {
            this.mTitle = jSONObject.getString("force_title");
        }
        if (JsonUtil.hasValue(jSONObject, "is_merchant")) {
            this.mIsMerchant = jSONObject.getBoolean("is_merchant");
        }
        if (JsonUtil.hasValue(jSONObject, "credit")) {
            this.mCredit = jSONObject.getString("credit");
        }
        this.mPreviews = JsonUtil.parseArray(jSONObject, "preview", new DataParser<WishImage, String>() {
            public WishImage parseData(String str) {
                return new WishImage(str);
            }
        });
    }

    public WishBrandFilter(String str) {
        this(str, null, null, null, null, null, false);
    }

    public WishBrandFilter(String str, String str2, String str3, String str4, String str5, String str6, boolean z) {
        this.mIsMerchant = z;
        this.mPreviews = new ArrayList<>();
        this.mQuery = str;
        this.mTitle = str2;
        this.mCredit = str6;
        this.mPrice = str3;
        this.mTag = str4;
        this.mProducts = str5;
        if (this.mTitle == null) {
            this.mTitle = this.mQuery;
        }
    }

    protected WishBrandFilter(Parcel parcel) {
        this.mIsMerchant = parcel.readByte() != 0;
        this.mPreviews = parcel.createTypedArrayList(WishImage.CREATOR);
        this.mCredit = parcel.readString();
        this.mPrice = parcel.readString();
        this.mProducts = parcel.readString();
        this.mQuery = parcel.readString();
        this.mTag = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public String getQuery() {
        return this.mQuery;
    }

    public String getPrice() {
        return this.mPrice;
    }

    public String getTag() {
        return this.mTag;
    }

    public String getProducts() {
        return this.mProducts;
    }

    public String getCredit() {
        return this.mCredit;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.mIsMerchant ? (byte) 1 : 0);
        parcel.writeTypedList(this.mPreviews);
        parcel.writeString(this.mCredit);
        parcel.writeString(this.mPrice);
        parcel.writeString(this.mProducts);
        parcel.writeString(this.mQuery);
        parcel.writeString(this.mTag);
        parcel.writeString(this.mTitle);
    }
}
