package com.contextlogic.wish.api.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NullableNonNullAnnotationRequired"})
public class PriceChopProductDetail extends BaseModel {
    public static final Creator<PriceChopProductDetail> CREATOR = new Creator<PriceChopProductDetail>() {
        public PriceChopProductDetail createFromParcel(Parcel parcel) {
            return new PriceChopProductDetail(parcel);
        }

        public PriceChopProductDetail[] newArray(int i) {
            return new PriceChopProductDetail[i];
        }
    };
    private String mBody;
    private Date mExpireDate;
    private boolean mIsRunning;
    private PriceChopStatuses mPriceChopStatuses;
    private String mShareBody;
    private String mShareTitle;
    private String mTitle;

    public int describeContents() {
        return 0;
    }

    public PriceChopProductDetail(JSONObject jSONObject) throws JSONException, ParseException {
        parseJson(jSONObject);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getBody() {
        return this.mBody;
    }

    public PriceChopStatuses getPriceChopStatuses() {
        return this.mPriceChopStatuses;
    }

    public Date getExpireDate() {
        return this.mExpireDate;
    }

    public boolean isRunning() {
        return this.mIsRunning;
    }

    public String getShareTitle() {
        return this.mShareTitle;
    }

    public String getShareBody() {
        return this.mShareBody;
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mBody = jSONObject.getString("body");
        this.mIsRunning = jSONObject.getBoolean("is_running");
        this.mPriceChopStatuses = new PriceChopStatuses(jSONObject.getJSONObject("price_chop_status"));
        long optLong = jSONObject.optLong("time_left_seconds", -1);
        if (optLong > 0) {
            this.mExpireDate = new Date(System.currentTimeMillis() + (optLong * 1000));
        }
        this.mShareTitle = jSONObject.getString("share_title");
        this.mShareBody = jSONObject.getString("share_body");
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mBody);
        parcel.writeParcelable(this.mPriceChopStatuses, i);
        parcel.writeLong(this.mExpireDate != null ? this.mExpireDate.getTime() : -1);
        parcel.writeByte(this.mIsRunning ? (byte) 1 : 0);
        parcel.writeString(this.mShareTitle);
        parcel.writeString(this.mShareBody);
    }

    protected PriceChopProductDetail(Parcel parcel) {
        Date date;
        this.mTitle = parcel.readString();
        this.mBody = parcel.readString();
        this.mPriceChopStatuses = (PriceChopStatuses) parcel.readParcelable(PriceChopStatuses.class.getClassLoader());
        long readLong = parcel.readLong();
        if (readLong == -1) {
            date = null;
        } else {
            date = new Date(readLong);
        }
        this.mExpireDate = date;
        this.mIsRunning = parcel.readByte() != 0;
        this.mShareTitle = parcel.readString();
        this.mShareBody = parcel.readString();
    }
}
