package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishShippingBadge extends BaseModel {
    public static final Creator<WishShippingBadge> CREATOR = new Creator<WishShippingBadge>() {
        public WishShippingBadge createFromParcel(Parcel parcel) {
            return new WishShippingBadge(parcel);
        }

        public WishShippingBadge[] newArray(int i) {
            return new WishShippingBadge[i];
        }
    };
    private String mColorString;
    private String mImageUrl;
    private String mSubtitle;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    WishShippingBadge(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mSubtitle = jSONObject.getString("subtitle");
        this.mColorString = jSONObject.getString("color");
        this.mImageUrl = jSONObject.getString("image_url");
    }

    WishShippingBadge(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mSubtitle = parcel.readString();
        this.mColorString = parcel.readString();
        this.mImageUrl = parcel.readString();
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public String getColorString() {
        return this.mColorString;
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mSubtitle);
        parcel.writeString(this.mColorString);
        parcel.writeString(this.mImageUrl);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishShippingBadge wishShippingBadge = (WishShippingBadge) obj;
        if (this.mTitle == null ? wishShippingBadge.mTitle != null : !this.mTitle.equals(wishShippingBadge.mTitle)) {
            return false;
        }
        if (this.mSubtitle == null ? wishShippingBadge.mSubtitle != null : !this.mSubtitle.equals(wishShippingBadge.mSubtitle)) {
            return false;
        }
        if (this.mColorString == null ? wishShippingBadge.mColorString != null : !this.mColorString.equals(wishShippingBadge.mColorString)) {
            return false;
        }
        if (this.mImageUrl != null) {
            z = this.mImageUrl.equals(wishShippingBadge.mImageUrl);
        } else if (wishShippingBadge.mImageUrl != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((this.mTitle != null ? this.mTitle.hashCode() : 0) * 31) + (this.mSubtitle != null ? this.mSubtitle.hashCode() : 0)) * 31) + (this.mColorString != null ? this.mColorString.hashCode() : 0)) * 31;
        if (this.mImageUrl != null) {
            i = this.mImageUrl.hashCode();
        }
        return hashCode + i;
    }
}
