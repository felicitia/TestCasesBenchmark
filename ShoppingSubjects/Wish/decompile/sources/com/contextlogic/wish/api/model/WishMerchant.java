package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishMerchant extends BaseModel implements Parcelable {
    public static final Creator<WishMerchant> CREATOR = new Creator<WishMerchant>() {
        public WishMerchant createFromParcel(Parcel parcel) {
            return new WishMerchant(parcel);
        }

        public WishMerchant[] newArray(int i) {
            return new WishMerchant[i];
        }
    };
    private long mApprovedDate;
    private String mDisplayName;
    private boolean mHasCategories;
    private String mImageUrl;
    private String mMerchantId;
    private int mPercentPositiveFeedback;
    private int mProductCount;
    private double mRating;
    private int mRatingCount;
    private ArrayList<WishTag> mTags;
    private String mUniqueName;

    public int describeContents() {
        return 0;
    }

    public WishMerchant(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mImageUrl = jSONObject.getString("display_pic");
        this.mMerchantId = jSONObject.getString("id");
        this.mUniqueName = jSONObject.getString("name");
        this.mDisplayName = jSONObject.getString("display_name");
        this.mRating = jSONObject.getDouble("avg_rating");
        this.mRatingCount = jSONObject.getInt("rating_count");
        this.mProductCount = jSONObject.getInt("product_count");
        this.mPercentPositiveFeedback = jSONObject.getInt("percent_positive_feedback");
        this.mTags = JsonUtil.parseArray(jSONObject, "top_tags", new DataParser<WishTag, JSONObject>() {
            public WishTag parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishTag(jSONObject);
            }
        });
        this.mApprovedDate = jSONObject.getLong("approved_date");
        this.mHasCategories = jSONObject.optBoolean("has_categories", false);
    }

    protected WishMerchant(Parcel parcel) {
        this.mRating = parcel.readDouble();
        this.mRatingCount = parcel.readInt();
        this.mProductCount = parcel.readInt();
        this.mPercentPositiveFeedback = parcel.readInt();
        this.mTags = parcel.createTypedArrayList(WishTag.CREATOR);
        this.mImageUrl = parcel.readString();
        this.mMerchantId = parcel.readString();
        this.mUniqueName = parcel.readString();
        this.mDisplayName = parcel.readString();
        this.mApprovedDate = parcel.readLong();
        this.mHasCategories = parcel.readByte() != 0;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public double getRating() {
        return this.mRating;
    }

    public int getRatingCount() {
        return this.mRatingCount;
    }

    public int getProductCount() {
        return this.mProductCount;
    }

    public int getPercentPositiveFeedback() {
        return this.mPercentPositiveFeedback;
    }

    public boolean getHasCategories() {
        return this.mHasCategories;
    }

    public Date getApprovedDate() {
        return new Date(this.mApprovedDate * 1000);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mRating);
        parcel.writeInt(this.mRatingCount);
        parcel.writeInt(this.mProductCount);
        parcel.writeInt(this.mPercentPositiveFeedback);
        parcel.writeTypedList(this.mTags);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mMerchantId);
        parcel.writeString(this.mUniqueName);
        parcel.writeString(this.mDisplayName);
        parcel.writeLong(this.mApprovedDate);
        parcel.writeByte(this.mHasCategories ? (byte) 1 : 0);
    }
}
