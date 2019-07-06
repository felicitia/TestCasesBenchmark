package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.SystemClock;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class WishDealDashInfo extends BaseModel implements Parcelable {
    public static final Creator<WishDealDashInfo> CREATOR = new Creator<WishDealDashInfo>() {
        public WishDealDashInfo createFromParcel(Parcel parcel) {
            return new WishDealDashInfo(parcel);
        }

        public WishDealDashInfo[] newArray(int i) {
            return new WishDealDashInfo[i];
        }
    };
    private long mCheckoutTime;
    private String mCouponCode;
    private Date mDateStart;
    private int mDiscountPercent;
    private long mPlayTime;
    private long mReferenceTime;
    private boolean mSeenTutorial;
    private long mSpinResult;
    private long mSpinTimeElapsed;
    private long mTimeElapsed;
    private long mWaitTime;

    public int describeContents() {
        return 0;
    }

    public WishDealDashInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        if (JsonUtil.hasValue(jSONObject, "constants")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("constants");
            this.mPlayTime = jSONObject2.getLong("play_time");
            this.mCheckoutTime = jSONObject2.getLong("checkout_time");
        }
        if (JsonUtil.hasValue(jSONObject, "time_elapsed")) {
            this.mTimeElapsed = jSONObject.getLong("time_elapsed");
            this.mReferenceTime = SystemClock.elapsedRealtime();
        }
        if (JsonUtil.hasValue(jSONObject, "wait_time")) {
            this.mWaitTime = jSONObject.getLong("wait_time");
        }
        this.mSpinTimeElapsed = jSONObject.optLong("spin_time_elapsed");
        this.mSpinResult = jSONObject.optLong("spin_result");
        if (JsonUtil.hasValue(jSONObject, "date_start")) {
            this.mDateStart = DateUtil.parseIsoDate(jSONObject.getString("date_start"));
        }
        this.mSeenTutorial = jSONObject.optBoolean("seen_tutorial");
        this.mDiscountPercent = jSONObject.optInt("discount_percent", 0);
        this.mCouponCode = jSONObject.optString("coupon_code", "");
    }

    protected WishDealDashInfo(Parcel parcel) {
        this.mWaitTime = parcel.readLong();
        this.mPlayTime = parcel.readLong();
        this.mCheckoutTime = parcel.readLong();
        this.mTimeElapsed = parcel.readLong();
        this.mSpinTimeElapsed = parcel.readLong();
        this.mSpinResult = parcel.readLong();
        this.mReferenceTime = parcel.readLong();
        if (parcel.readByte() != 0) {
            this.mDateStart = new Date(parcel.readLong());
        }
        this.mSeenTutorial = parcel.readByte() != 0;
        this.mDiscountPercent = parcel.readInt();
        this.mCouponCode = parcel.readString();
    }

    public boolean getSeenTutorial() {
        return this.mSeenTutorial;
    }

    public long getWaitTime() {
        return this.mWaitTime;
    }

    public long getPlayTime() {
        return this.mPlayTime;
    }

    public String getDiscountPercentString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s", new Object[]{Integer.valueOf(this.mDiscountPercent)}));
        sb.append("%");
        return sb.toString();
    }

    public String getCouponCode() {
        return this.mCouponCode.toUpperCase();
    }

    public long getTimeElapsed() {
        return this.mTimeElapsed + TimeUnit.SECONDS.convert(SystemClock.elapsedRealtime() - this.mReferenceTime, TimeUnit.MILLISECONDS);
    }

    public long getSpinTimeElapsed() {
        if (this.mSpinTimeElapsed == 0) {
            return 0;
        }
        return this.mSpinTimeElapsed + TimeUnit.SECONDS.convert(SystemClock.elapsedRealtime() - this.mReferenceTime, TimeUnit.MILLISECONDS);
    }

    public boolean hasPlayedToday() {
        boolean z = false;
        if (this.mDateStart == null) {
            return false;
        }
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        if (this.mDateStart.getTime() > instance.getTime().getTime()) {
            z = true;
        }
        return z;
    }

    public long getSpinResult() {
        return this.mSpinResult;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mWaitTime);
        parcel.writeLong(this.mPlayTime);
        parcel.writeLong(this.mCheckoutTime);
        parcel.writeLong(this.mTimeElapsed);
        parcel.writeLong(this.mSpinTimeElapsed);
        parcel.writeLong(this.mSpinResult);
        parcel.writeLong(this.mReferenceTime);
        parcel.writeByte((byte) (this.mDateStart != null ? 1 : 0));
        if (this.mDateStart != null) {
            parcel.writeLong(this.mDateStart.getTime());
        }
        parcel.writeByte(this.mSeenTutorial ? (byte) 1 : 0);
        parcel.writeInt(this.mDiscountPercent);
        parcel.writeString(this.mCouponCode);
    }
}
