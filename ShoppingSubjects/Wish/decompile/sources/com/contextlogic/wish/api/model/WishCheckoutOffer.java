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

public class WishCheckoutOffer extends BaseModel implements Parcelable {
    public static final Creator<WishCheckoutOffer> CREATOR = new Creator<WishCheckoutOffer>() {
        public WishCheckoutOffer createFromParcel(Parcel parcel) {
            return new WishCheckoutOffer(parcel);
        }

        public WishCheckoutOffer[] newArray(int i) {
            return new WishCheckoutOffer[i];
        }
    };
    private Date mExpiry;
    private String mMessage;
    private String mOfferId;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishCheckoutOffer(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        this.mOfferId = jSONObject.getString("id");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
    }

    protected WishCheckoutOffer(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        this.mOfferId = parcel.readString();
        this.mTitle = parcel.readString();
        this.mMessage = parcel.readString();
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

    public String getMessage() {
        return this.mMessage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        parcel.writeString(this.mOfferId);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mMessage);
    }
}
