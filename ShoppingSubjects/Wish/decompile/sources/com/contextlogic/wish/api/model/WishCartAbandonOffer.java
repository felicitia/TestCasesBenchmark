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

public class WishCartAbandonOffer extends BaseModel implements Parcelable {
    public static final Creator<WishCartAbandonOffer> CREATOR = new Creator<WishCartAbandonOffer>() {
        public WishCartAbandonOffer createFromParcel(Parcel parcel) {
            return new WishCartAbandonOffer(parcel);
        }

        public WishCartAbandonOffer[] newArray(int i) {
            return new WishCartAbandonOffer[i];
        }
    };
    private String mDismissText;
    private Date mExpiry;
    private String mImageUrl;
    private String mMessage;
    private String mOfferId;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishCartAbandonOffer(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        this.mDismissText = jSONObject.getString("dismiss");
        this.mImageUrl = jSONObject.getString("image_url");
        this.mMessage = jSONObject.getString("message");
        this.mOfferId = jSONObject.getString("id");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
    }

    protected WishCartAbandonOffer(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        this.mDismissText = parcel.readString();
        this.mImageUrl = parcel.readString();
        this.mMessage = parcel.readString();
        this.mOfferId = parcel.readString();
        this.mTitle = parcel.readString();
    }

    public boolean isExpired() {
        return this.mExpiry.before(new Date());
    }

    public String getImageUrl() {
        return this.mImageUrl;
    }

    public String getOfferId() {
        return this.mOfferId;
    }

    public String getDismissText() {
        return this.mDismissText;
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
        parcel.writeString(this.mDismissText);
        parcel.writeString(this.mImageUrl);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mOfferId);
        parcel.writeString(this.mTitle);
    }
}
