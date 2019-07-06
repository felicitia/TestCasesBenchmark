package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class LoggedOutCountdownCoupon extends BaseModel {
    public static final Creator<LoggedOutCountdownCoupon> CREATOR = new Creator<LoggedOutCountdownCoupon>() {
        public LoggedOutCountdownCoupon createFromParcel(Parcel parcel) {
            return new LoggedOutCountdownCoupon(parcel);
        }

        public LoggedOutCountdownCoupon[] newArray(int i) {
            return new LoggedOutCountdownCoupon[i];
        }
    };
    private boolean mClaimed;
    private String mCouponId;
    private int mDiscountPercent;
    private Date mExpiryTime;
    private String mFormattedMaxDiscount;
    private String mId;
    private int mTotalMinutes;
    private String mUserId;

    public int describeContents() {
        return 0;
    }

    public LoggedOutCountdownCoupon(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiryTime = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        this.mId = jSONObject.getString("id");
        this.mUserId = jSONObject.getString("user_id");
        this.mCouponId = jSONObject.getString("coupon_id");
        this.mFormattedMaxDiscount = jSONObject.getString("formatted_max_discount");
        this.mClaimed = jSONObject.optBoolean("claimed");
        this.mDiscountPercent = jSONObject.getInt("discount_percent");
        this.mTotalMinutes = jSONObject.getInt("total_minutes");
    }

    protected LoggedOutCountdownCoupon(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiryTime = new Date(parcel.readLong());
        }
        this.mId = parcel.readString();
        this.mUserId = parcel.readString();
        this.mCouponId = parcel.readString();
        this.mFormattedMaxDiscount = parcel.readString();
        this.mClaimed = parcel.readByte() != 0;
        this.mDiscountPercent = parcel.readInt();
        this.mTotalMinutes = parcel.readInt();
    }

    public Date getExpiry() {
        return this.mExpiryTime;
    }

    public String getCouponId() {
        return this.mCouponId;
    }

    public String getFormattedMaxDiscount() {
        return this.mFormattedMaxDiscount;
    }

    public int getDiscountPercent() {
        return this.mDiscountPercent;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiryTime != null ? 1 : 0));
        if (this.mExpiryTime != null) {
            parcel.writeLong(this.mExpiryTime.getTime());
        }
        parcel.writeString(this.mId);
        parcel.writeString(this.mUserId);
        parcel.writeString(this.mCouponId);
        parcel.writeString(this.mFormattedMaxDiscount);
        parcel.writeByte(this.mClaimed ? (byte) 1 : 0);
        parcel.writeInt(this.mDiscountPercent);
        parcel.writeInt(this.mTotalMinutes);
    }
}
