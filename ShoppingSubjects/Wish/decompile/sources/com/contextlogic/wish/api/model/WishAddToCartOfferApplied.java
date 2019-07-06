package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishAddToCartOfferApplied extends BaseModel implements Parcelable {
    public static final Creator<WishAddToCartOfferApplied> CREATOR = new Creator<WishAddToCartOfferApplied>() {
        public WishAddToCartOfferApplied createFromParcel(Parcel parcel) {
            return new WishAddToCartOfferApplied(parcel);
        }

        public WishAddToCartOfferApplied[] newArray(int i) {
            return new WishAddToCartOfferApplied[i];
        }
    };
    private Date mExpiry;
    private String mMessage;
    private String mOfferId;
    private WishImage mProductImage;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public WishAddToCartOfferApplied(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "expiry")) {
            this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        }
        this.mOfferId = jSONObject.getString("id");
        this.mMessage = jSONObject.getString("message");
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mProductImage = new WishImage(jSONObject.getString("product_image_url"));
    }

    protected WishAddToCartOfferApplied(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        this.mOfferId = parcel.readString();
        this.mMessage = parcel.readString();
        this.mTitle = parcel.readString();
        this.mProductImage = (WishImage) parcel.readParcelable(WishImage.class.getClassLoader());
    }

    public WishImage getProductImage() {
        return this.mProductImage;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public Date getExpiry() {
        return this.mExpiry;
    }

    public boolean isExpired() {
        return this.mExpiry.before(new Date());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        parcel.writeString(this.mOfferId);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mTitle);
        parcel.writeParcelable(this.mProductImage, 0);
    }
}
