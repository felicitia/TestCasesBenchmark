package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishBrand extends BaseModel implements Parcelable {
    public static final Creator<WishBrand> CREATOR = new Creator<WishBrand>() {
        public WishBrand createFromParcel(Parcel parcel) {
            return new WishBrand(parcel);
        }

        public WishBrand[] newArray(int i) {
            return new WishBrand[i];
        }
    };
    private String mBrandId;
    private double mLogoAspectRatio;
    private String mLogoUrl;
    private String mName;

    public int describeContents() {
        return 0;
    }

    public WishBrand(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException {
        this.mName = jSONObject.getString("name");
        this.mBrandId = jSONObject.getString("brand_id");
        this.mLogoUrl = jSONObject.getString("logo_url");
        this.mLogoAspectRatio = jSONObject.getDouble("logo_aspect_ratio");
    }

    protected WishBrand(Parcel parcel) {
        this.mName = parcel.readString();
        this.mBrandId = parcel.readString();
        this.mLogoUrl = parcel.readString();
        this.mLogoAspectRatio = parcel.readDouble();
    }

    public String getLogoUrl() {
        return this.mLogoUrl;
    }

    public double getLogoAspectRatio() {
        return this.mLogoAspectRatio;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mBrandId);
        parcel.writeString(this.mLogoUrl);
        parcel.writeDouble(this.mLogoAspectRatio);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishBrand wishBrand = (WishBrand) obj;
        if (Double.compare(wishBrand.mLogoAspectRatio, this.mLogoAspectRatio) != 0) {
            return false;
        }
        if (this.mName == null ? wishBrand.mName != null : !this.mName.equals(wishBrand.mName)) {
            return false;
        }
        if (this.mBrandId == null ? wishBrand.mBrandId != null : !this.mBrandId.equals(wishBrand.mBrandId)) {
            return false;
        }
        if (this.mLogoUrl != null) {
            z = this.mLogoUrl.equals(wishBrand.mLogoUrl);
        } else if (wishBrand.mLogoUrl != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.mName != null ? this.mName.hashCode() : 0) * 31) + (this.mBrandId != null ? this.mBrandId.hashCode() : 0)) * 31;
        if (this.mLogoUrl != null) {
            i = this.mLogoUrl.hashCode();
        }
        int i2 = hashCode + i;
        long doubleToLongBits = Double.doubleToLongBits(this.mLogoAspectRatio);
        return (i2 * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
    }
}
