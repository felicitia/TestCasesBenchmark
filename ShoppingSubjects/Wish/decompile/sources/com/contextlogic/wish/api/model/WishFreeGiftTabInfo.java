package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.analytics.WishAnalyticsLogger;
import com.contextlogic.wish.analytics.WishAnalyticsLogger.WishAnalyticsEvent;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.PreferenceUtil;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class WishFreeGiftTabInfo extends BaseModel implements Parcelable {
    public static final Creator<WishFreeGiftTabInfo> CREATOR = new Creator<WishFreeGiftTabInfo>() {
        public WishFreeGiftTabInfo createFromParcel(Parcel parcel) {
            return new WishFreeGiftTabInfo(parcel);
        }

        public WishFreeGiftTabInfo[] newArray(int i) {
            return new WishFreeGiftTabInfo[i];
        }
    };
    private String mBannerTitle;
    private String mClaimText;
    private Date mExpiry;
    private String mHeaderSubtitle;
    private String mHeaderTitle;
    private boolean mShowBanner;
    private boolean mShowSplash;
    private String mSplashSubTitle;
    private String mSplashTitle;

    public int describeContents() {
        return 0;
    }

    public WishFreeGiftTabInfo(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mExpiry = DateUtil.parseIsoDate(jSONObject.getString("expiry"));
        PreferenceUtil.setLong("FreeGiftsTabExpiry", this.mExpiry.getTime());
        this.mShowSplash = jSONObject.getBoolean("show_splash");
        this.mShowBanner = jSONObject.getBoolean("show_banner");
        this.mSplashTitle = jSONObject.getString("splash_title");
        this.mSplashSubTitle = jSONObject.getString("splash_subtitle");
        this.mClaimText = jSONObject.getString("claim_text");
        this.mHeaderTitle = jSONObject.getString("header_title");
        this.mHeaderSubtitle = jSONObject.getString("header_subtitle");
        this.mBannerTitle = jSONObject.getString("banner_title");
    }

    protected WishFreeGiftTabInfo(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiry = new Date(parcel.readLong());
        }
        boolean z = false;
        this.mShowSplash = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mShowBanner = z;
        this.mSplashTitle = parcel.readString();
        this.mSplashSubTitle = parcel.readString();
        this.mClaimText = parcel.readString();
        this.mHeaderTitle = parcel.readString();
        this.mHeaderSubtitle = parcel.readString();
        this.mBannerTitle = parcel.readString();
    }

    public Date getExpiry() {
        return this.mExpiry;
    }

    public boolean showSplash() {
        return this.mShowSplash;
    }

    public boolean showBanner() {
        return this.mShowBanner;
    }

    public String getSplashTitle() {
        return this.mSplashTitle;
    }

    public String getSplashSubTitle() {
        return this.mSplashSubTitle;
    }

    public String getClaimText() {
        return this.mClaimText;
    }

    public String getHeaderTitle() {
        return this.mHeaderTitle;
    }

    public String getHeaderSubtitle() {
        return this.mHeaderSubtitle;
    }

    public static void logFreeGiftTabEvent(WishAnalyticsEvent wishAnalyticsEvent) {
        long j = PreferenceUtil.getLong("FreeGiftsTabExpiry", 0) - new Date().getTime();
        HashMap hashMap = new HashMap();
        hashMap.put("free_gifts_tab_time_remaining", String.format(Locale.getDefault(), "%d%n", new Object[]{Long.valueOf(j)}));
        WishAnalyticsLogger.trackEvent(wishAnalyticsEvent.getValue(), (String) null, hashMap);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiry != null ? 1 : 0));
        if (this.mExpiry != null) {
            parcel.writeLong(this.mExpiry.getTime());
        }
        parcel.writeByte(this.mShowSplash ? (byte) 1 : 0);
        parcel.writeByte(this.mShowBanner ? (byte) 1 : 0);
        parcel.writeString(this.mSplashTitle);
        parcel.writeString(this.mSplashSubTitle);
        parcel.writeString(this.mClaimText);
        parcel.writeString(this.mHeaderTitle);
        parcel.writeString(this.mHeaderSubtitle);
        parcel.writeString(this.mBannerTitle);
    }
}
