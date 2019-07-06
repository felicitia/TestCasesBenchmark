package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NullableNonNullAnnotationRequired"})
public class PriceChopStatus extends BaseModel {
    public static final Creator<PriceChopStatus> CREATOR = new Creator<PriceChopStatus>() {
        public PriceChopStatus createFromParcel(Parcel parcel) {
            return new PriceChopStatus(parcel);
        }

        public PriceChopStatus[] newArray(int i) {
            return new PriceChopStatus[i];
        }
    };
    private WishLocalizedCurrencyValue mPrice;
    private String mUserName;
    private String mUserProfilePictureUrl;

    public int describeContents() {
        return 0;
    }

    public PriceChopStatus(JSONObject jSONObject) throws JSONException, ParseException {
        parseJson(jSONObject);
    }

    public String getUserName() {
        return this.mUserName;
    }

    public String getUserProfilePictureUrl() {
        return this.mUserProfilePictureUrl;
    }

    public WishLocalizedCurrencyValue getPrice() {
        return this.mPrice;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mUserName = JsonUtil.optString(jSONObject, "user_name");
        this.mUserProfilePictureUrl = JsonUtil.optString(jSONObject, "user_profile_picture_url");
        this.mPrice = new WishLocalizedCurrencyValue(jSONObject.getDouble("price"), jSONObject.getJSONObject("price_localized"));
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mUserName);
        parcel.writeString(this.mUserProfilePictureUrl);
        parcel.writeParcelable(this.mPrice, i);
    }

    protected PriceChopStatus(Parcel parcel) {
        this.mUserName = parcel.readString();
        this.mUserProfilePictureUrl = parcel.readString();
        this.mPrice = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
    }
}
