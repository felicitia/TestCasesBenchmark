package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRatingSummary extends BaseModel implements Parcelable {
    public static final Creator<WishRatingSummary> CREATOR = new Creator<WishRatingSummary>() {
        public WishRatingSummary createFromParcel(Parcel parcel) {
            return new WishRatingSummary(parcel);
        }

        public WishRatingSummary[] newArray(int i) {
            return new WishRatingSummary[i];
        }
    };
    private String mDisplayName;
    private String mImageUrl;
    private String mName;
    private double mRating;
    private long mRatingCount;
    private long mReviewCount;
    private List<Integer> mReviewSpreads;

    public int describeContents() {
        return 0;
    }

    public WishRatingSummary(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getString("display_picture_url") != null) {
            this.mImageUrl = jSONObject.getString("display_picture_url");
        }
        this.mName = jSONObject.getString("name");
        if (JsonUtil.hasValue(jSONObject, "display_name")) {
            setDisplayName(jSONObject.getString("display_name"));
        } else {
            setDisplayName(getName());
        }
        if (JsonUtil.hasValue(jSONObject, "review_spread")) {
            this.mReviewSpreads = new ArrayList(6);
            JSONObject jSONObject2 = jSONObject.getJSONObject("review_spread");
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("star_one_review_count")));
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("star_two_review_count")));
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("star_three_review_count")));
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("star_four_review_count")));
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("star_five_review_count")));
            for (Integer intValue : this.mReviewSpreads) {
                this.mReviewCount += (long) intValue.intValue();
            }
            this.mReviewSpreads.add(Integer.valueOf(jSONObject2.optInt("photo_review_count")));
        }
        this.mRating = jSONObject.getDouble("rating");
        this.mRatingCount = jSONObject.getLong("rating_count");
    }

    protected WishRatingSummary(Parcel parcel) {
        this.mRating = parcel.readDouble();
        this.mRatingCount = parcel.readLong();
        this.mReviewCount = parcel.readLong();
        if (parcel.readByte() != 0) {
            this.mReviewSpreads = new ArrayList();
            for (int valueOf : parcel.createIntArray()) {
                this.mReviewSpreads.add(Integer.valueOf(valueOf));
            }
        }
        this.mImageUrl = parcel.readString();
        this.mName = parcel.readString();
        this.mDisplayName = parcel.readString();
    }

    public long getRatingCount() {
        return this.mRatingCount;
    }

    public long getReviewCount() {
        return this.mReviewCount;
    }

    public double getRating() {
        return this.mRating;
    }

    public String getName() {
        return this.mName;
    }

    public String getDisplayName() {
        return this.mDisplayName;
    }

    public void setDisplayName(String str) {
        this.mDisplayName = str;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public List<Integer> getReviewSpreads() {
        return this.mReviewSpreads;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.mRating);
        parcel.writeLong(this.mRatingCount);
        parcel.writeLong(this.mReviewCount);
        parcel.writeByte((byte) (this.mReviewSpreads != null ? 1 : 0));
        if (this.mReviewSpreads != null) {
            int[] iArr = new int[this.mReviewSpreads.size()];
            for (int i2 = 0; i2 < this.mReviewSpreads.size(); i2++) {
                iArr[i2] = ((Integer) this.mReviewSpreads.get(i2)).intValue();
            }
            parcel.writeIntArray(iArr);
        }
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mName);
        parcel.writeString(this.mDisplayName);
    }
}
