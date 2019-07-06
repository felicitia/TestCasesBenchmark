package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishAddToCartOffer extends BaseModel implements Parcelable {
    public static final Creator<WishAddToCartOffer> CREATOR = new Creator<WishAddToCartOffer>() {
        public WishAddToCartOffer createFromParcel(Parcel parcel) {
            return new WishAddToCartOffer(parcel);
        }

        public WishAddToCartOffer[] newArray(int i) {
            return new WishAddToCartOffer[i];
        }
    };
    private Date mExpiry;
    private String mOfferId;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishAddToCartOffer(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        this.mOfferId = jSONObject.getString("id");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
    }

    protected WishAddToCartOffer(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        this.mOfferId = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public boolean isExpired() {
        return this.mExpiry.before(new Date());
    }

    public Date getExpiry() {
        return this.mExpiry;
    }

    public String getOfferId() {
        return this.mOfferId;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        parcel.writeString(this.mOfferId);
        parcel.writeString(this.mTitle);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishAddToCartOffer wishAddToCartOffer = (WishAddToCartOffer) obj;
        if (this.mExpiry == null ? wishAddToCartOffer.mExpiry != null : !this.mExpiry.equals(wishAddToCartOffer.mExpiry)) {
            return false;
        }
        if (this.mOfferId == null ? wishAddToCartOffer.mOfferId != null : !this.mOfferId.equals(wishAddToCartOffer.mOfferId)) {
            return false;
        }
        if (this.mTitle != null) {
            z = this.mTitle.equals(wishAddToCartOffer.mTitle);
        } else if (wishAddToCartOffer.mTitle != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((this.mExpiry != null ? this.mExpiry.hashCode() : 0) * 31) + (this.mOfferId != null ? this.mOfferId.hashCode() : 0)) * 31;
        if (this.mTitle != null) {
            i = this.mTitle.hashCode();
        }
        return hashCode + i;
    }
}
