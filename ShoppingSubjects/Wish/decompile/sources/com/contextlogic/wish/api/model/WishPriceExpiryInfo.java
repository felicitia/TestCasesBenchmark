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

public class WishPriceExpiryInfo extends BaseModel implements Parcelable {
    public static final Creator<WishPriceExpiryInfo> CREATOR = new Creator<WishPriceExpiryInfo>() {
        public WishPriceExpiryInfo createFromParcel(Parcel parcel) {
            return new WishPriceExpiryInfo(parcel);
        }

        public WishPriceExpiryInfo[] newArray(int i) {
            return new WishPriceExpiryInfo[i];
        }
    };
    private String mButtonText;
    private Date mExpiry;
    private String mMessage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishPriceExpiryInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mMessage = jSONObject.getString("message");
        this.mButtonText = jSONObject.getString("button");
    }

    protected WishPriceExpiryInfo(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        this.mButtonText = parcel.readString();
        this.mMessage = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public boolean isExpired() {
        return this.mExpiry.before(new Date());
    }

    public Date getExpiry() {
        return this.mExpiry;
    }

    public String getButtonText() {
        return this.mButtonText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        parcel.writeString(this.mButtonText);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mTitle);
    }
}
