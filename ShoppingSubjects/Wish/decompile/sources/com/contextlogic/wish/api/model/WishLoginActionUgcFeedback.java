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

public class WishLoginActionUgcFeedback extends BaseModel implements Parcelable {
    public static final Creator<WishLoginActionUgcFeedback> CREATOR = new Creator<WishLoginActionUgcFeedback>() {
        public WishLoginActionUgcFeedback createFromParcel(Parcel parcel) {
            return new WishLoginActionUgcFeedback(parcel);
        }

        public WishLoginActionUgcFeedback[] newArray(int i) {
            return new WishLoginActionUgcFeedback[i];
        }
    };
    private String mDescriptionString;
    private String mIntervalText;
    private WishUgcRatableProduct mRatableProduct;
    private ArrayList<WishUgcRatingViews> mRatings;
    private int mTotalViews;

    public int describeContents() {
        return 0;
    }

    protected WishLoginActionUgcFeedback(Parcel parcel) {
        this.mIntervalText = parcel.readString();
        this.mTotalViews = parcel.readInt();
        this.mDescriptionString = parcel.readString();
        this.mRatings = parcel.createTypedArrayList(WishUgcRatingViews.CREATOR);
        this.mRatableProduct = (WishUgcRatableProduct) parcel.readParcelable(WishUgcRatableProduct.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIntervalText);
        parcel.writeInt(this.mTotalViews);
        parcel.writeString(this.mDescriptionString);
        parcel.writeTypedList(this.mRatings);
        parcel.writeParcelable(this.mRatableProduct, 0);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mIntervalText = JsonUtil.optString(jSONObject, "interval_length_days");
        this.mTotalViews = jSONObject.optInt("total_rating_views", 0);
        this.mDescriptionString = JsonUtil.optString(jSONObject, "description_string");
        this.mRatings = JsonUtil.parseArray(jSONObject, "results", new DataParser<WishUgcRatingViews, JSONObject>() {
            public WishUgcRatingViews parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishUgcRatingViews(jSONObject);
            }
        });
        if (JsonUtil.hasValue(jSONObject, "product_to_rate")) {
            this.mRatableProduct = new WishUgcRatableProduct(jSONObject.getJSONObject("product_to_rate"));
        }
    }

    public WishLoginActionUgcFeedback(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    public String getIntervalText() {
        return this.mIntervalText;
    }

    public int getTotalViews() {
        return this.mTotalViews;
    }

    public String getDescriptionString() {
        return this.mDescriptionString;
    }

    public WishUgcRatableProduct getRatableProduct() {
        return this.mRatableProduct;
    }
}
