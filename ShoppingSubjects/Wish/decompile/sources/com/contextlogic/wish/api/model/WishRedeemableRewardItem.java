package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishRedeemableRewardItem extends BaseModel implements Parcelable {
    public static final Creator<WishRedeemableRewardItem> CREATOR = new Creator<WishRedeemableRewardItem>() {
        public WishRedeemableRewardItem createFromParcel(Parcel parcel) {
            return new WishRedeemableRewardItem(parcel);
        }

        public WishRedeemableRewardItem[] newArray(int i) {
            return new WishRedeemableRewardItem[i];
        }
    };
    private String mBadgeText;
    private String mCancelRedeemButtonText;
    private String mDescription;
    private String mDialogDescription;
    private String mDialogTitle;
    private boolean mDisabled;
    private String mDiscountBadgeText;
    private boolean mExpiring;
    private String mExpiryText;
    private boolean mIsExpired;
    private boolean mIsHeader = false;
    private int mPointsNeeded;
    private String mPointsText;
    private String mPromoCode;
    private String mRedeemButtonText;
    private int mRewardType;
    private String mTitle;
    private boolean mUsed;

    public int describeContents() {
        return 0;
    }

    public WishRedeemableRewardItem(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishRedeemableRewardItem(Parcel parcel) {
        boolean z = false;
        this.mRewardType = parcel.readInt();
        this.mPointsText = parcel.readString();
        this.mBadgeText = parcel.readString();
        this.mDiscountBadgeText = parcel.readString();
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mDialogTitle = parcel.readString();
        this.mDialogDescription = parcel.readString();
        this.mRedeemButtonText = parcel.readString();
        this.mCancelRedeemButtonText = parcel.readString();
        this.mExpiring = parcel.readByte() != 0;
        this.mDisabled = parcel.readByte() != 0;
        this.mIsExpired = parcel.readByte() != 0;
        if (parcel.readByte() != 0) {
            z = true;
        }
        this.mUsed = z;
        this.mPromoCode = parcel.readString();
        this.mPointsNeeded = parcel.readInt();
        this.mExpiryText = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mRewardType);
        parcel.writeString(this.mPointsText);
        parcel.writeString(this.mBadgeText);
        parcel.writeString(this.mDiscountBadgeText);
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mDialogTitle);
        parcel.writeString(this.mDialogDescription);
        parcel.writeString(this.mRedeemButtonText);
        parcel.writeString(this.mCancelRedeemButtonText);
        parcel.writeByte(this.mExpiring ? (byte) 1 : 0);
        parcel.writeByte(this.mDisabled ? (byte) 1 : 0);
        parcel.writeByte(this.mIsExpired ? (byte) 1 : 0);
        parcel.writeByte(this.mUsed ? (byte) 1 : 0);
        parcel.writeString(this.mPromoCode);
        parcel.writeInt(this.mPointsNeeded);
        parcel.writeString(this.mExpiryText);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mRewardType = jSONObject.optInt("reward_type");
        this.mPointsText = JsonUtil.optString(jSONObject, "right_badge_text");
        this.mBadgeText = JsonUtil.optString(jSONObject, "left_badge_text");
        this.mDiscountBadgeText = JsonUtil.optString(jSONObject, "discount_badge_text");
        this.mTitle = JsonUtil.optString(jSONObject, "title_text");
        this.mDescription = JsonUtil.optString(jSONObject, "description_text");
        this.mDialogTitle = JsonUtil.optString(jSONObject, "dialog_title_text");
        this.mDialogDescription = JsonUtil.optString(jSONObject, "dialog_description_text");
        this.mRedeemButtonText = JsonUtil.optString(jSONObject, "redeem_button_text");
        this.mCancelRedeemButtonText = JsonUtil.optString(jSONObject, "cancel_redeem_button_text");
        this.mExpiring = jSONObject.optBoolean("expiring");
        this.mDisabled = jSONObject.optBoolean("disabled");
        this.mIsExpired = jSONObject.optBoolean("expired");
        this.mUsed = jSONObject.optBoolean("used");
        this.mPromoCode = JsonUtil.optString(jSONObject, "promo_code");
        this.mPointsNeeded = jSONObject.optInt("points_needed");
        this.mExpiryText = JsonUtil.optString(jSONObject, "expiry_text");
    }

    public int getRewardType() {
        return this.mRewardType;
    }

    public String getPointsText() {
        return this.mPointsText;
    }

    public String getBadgeText() {
        return this.mBadgeText;
    }

    public String getDiscountBadgeText() {
        return this.mDiscountBadgeText;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public String getDialogTitle() {
        return this.mDialogTitle;
    }

    public String getDialogDescription() {
        return this.mDialogDescription;
    }

    public String getRedeemButtonText() {
        return this.mRedeemButtonText;
    }

    public String getCancelRedeemButtonText() {
        return this.mCancelRedeemButtonText;
    }

    public boolean isDisabled() {
        return this.mDisabled;
    }

    public boolean isExpiring() {
        return this.mExpiring;
    }

    public boolean isExpired() {
        return this.mIsExpired;
    }

    public boolean isUsed() {
        return this.mUsed;
    }

    public String getPromoCode() {
        return this.mPromoCode;
    }

    public String getExpiryText() {
        return this.mExpiryText;
    }
}
