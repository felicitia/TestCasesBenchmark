package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.JsonUtil;
import com.contextlogic.wish.util.JsonUtil.DataParser;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class WishReferralProgramInfoSpec extends BaseModel {
    public static final Creator<WishReferralProgramInfoSpec> CREATOR = new Creator<WishReferralProgramInfoSpec>() {
        public WishReferralProgramInfoSpec createFromParcel(Parcel parcel) {
            return new WishReferralProgramInfoSpec(parcel);
        }

        public WishReferralProgramInfoSpec[] newArray(int i) {
            return new WishReferralProgramInfoSpec[i];
        }
    };
    private String mCouponCode;
    private String mFormattedEarnableAmount;
    private List<WishTextViewSpec> mInfoBodyTextSpecs;
    private List<ReferralProgramHistoryItem> mReferralHistoryItems;
    private String mShareCouponMessage;
    private String mShareCouponSubject;
    private List<WishTextViewSpec> mWarningTextSpecs;
    private String mWishCashEarnedTextSpec;
    private String mWishCashInfoBody;
    private String mWishCashInfoTitle;

    public static class ReferralProgramHistoryItem extends BaseModel {
        public static final Creator<ReferralProgramHistoryItem> CREATOR = new Creator<ReferralProgramHistoryItem>() {
            public ReferralProgramHistoryItem createFromParcel(Parcel parcel) {
                return new ReferralProgramHistoryItem(parcel);
            }

            public ReferralProgramHistoryItem[] newArray(int i) {
                return new ReferralProgramHistoryItem[i];
            }
        };
        private boolean mIsComplete;
        private boolean mIsInviteLink;
        private WishTextViewSpec mReferralName;
        private WishTextViewSpec mStatusText;

        public int describeContents() {
            return 0;
        }

        ReferralProgramHistoryItem(JSONObject jSONObject) throws JSONException, ParseException {
            super(jSONObject);
        }

        /* access modifiers changed from: protected */
        public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
            if (jSONObject.has("referral_name_text")) {
                this.mReferralName = new WishTextViewSpec(jSONObject.getJSONObject("referral_name_text"));
            }
            if (jSONObject.has("status_text")) {
                this.mStatusText = new WishTextViewSpec(jSONObject.getJSONObject("status_text"));
            }
            this.mIsComplete = jSONObject.optBoolean("is_complete");
            this.mIsInviteLink = jSONObject.optBoolean("is_invite");
        }

        ReferralProgramHistoryItem(Parcel parcel) {
            this.mReferralName = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            this.mStatusText = (WishTextViewSpec) parcel.readParcelable(WishTextViewSpec.class.getClassLoader());
            boolean z = false;
            this.mIsComplete = parcel.readByte() != 0;
            if (parcel.readByte() != 0) {
                z = true;
            }
            this.mIsInviteLink = z;
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeParcelable(this.mReferralName, i);
            parcel.writeParcelable(this.mStatusText, i);
            parcel.writeByte(this.mIsComplete ? (byte) 1 : 0);
            parcel.writeByte(this.mIsInviteLink ? (byte) 1 : 0);
        }

        public WishTextViewSpec getReferralNameTextSpec() {
            return this.mReferralName;
        }

        public WishTextViewSpec getStatusTextSpec() {
            return this.mStatusText;
        }

        public boolean isComplete() {
            return this.mIsComplete;
        }

        public boolean isInviteLink() {
            return this.mIsInviteLink;
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishReferralProgramInfoSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mFormattedEarnableAmount = jSONObject.optString("formatted_earnable_amount");
        this.mInfoBodyTextSpecs = JsonUtil.parseArray(jSONObject, "info_body_texts", new DataParser<WishTextViewSpec, JSONObject>() {
            public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishTextViewSpec(jSONObject);
            }
        });
        this.mCouponCode = jSONObject.getString("promo_code");
        this.mWishCashEarnedTextSpec = jSONObject.optString("formatted_wish_cash_earned_text");
        this.mReferralHistoryItems = JsonUtil.parseArray(jSONObject, "referral_history_items", new DataParser<ReferralProgramHistoryItem, JSONObject>() {
            public ReferralProgramHistoryItem parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new ReferralProgramHistoryItem(jSONObject);
            }
        });
        this.mShareCouponSubject = jSONObject.optString("share_subject");
        this.mShareCouponMessage = jSONObject.optString("share_message");
        this.mWishCashInfoTitle = jSONObject.optString("wish_cash_info_title");
        this.mWishCashInfoBody = jSONObject.optString("wish_cash_info_body");
        this.mWarningTextSpecs = JsonUtil.parseArray(jSONObject, "warning_texts", new DataParser<WishTextViewSpec, JSONObject>() {
            public WishTextViewSpec parseData(JSONObject jSONObject) throws JSONException, ParseException {
                return new WishTextViewSpec(jSONObject);
            }
        });
    }

    protected WishReferralProgramInfoSpec(Parcel parcel) {
        this.mFormattedEarnableAmount = parcel.readString();
        this.mInfoBodyTextSpecs = parcel.readArrayList(WishTextViewSpec.class.getClassLoader());
        this.mCouponCode = parcel.readString();
        this.mWishCashEarnedTextSpec = parcel.readString();
        this.mReferralHistoryItems = parcel.readArrayList(ReferralProgramHistoryItem.class.getClassLoader());
        this.mShareCouponSubject = parcel.readString();
        this.mShareCouponMessage = parcel.readString();
        this.mWishCashInfoTitle = parcel.readString();
        this.mWishCashInfoBody = parcel.readString();
        this.mWarningTextSpecs = parcel.readArrayList(WishTextViewSpec.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mFormattedEarnableAmount);
        parcel.writeList(this.mInfoBodyTextSpecs);
        parcel.writeString(this.mCouponCode);
        parcel.writeString(this.mWishCashEarnedTextSpec);
        parcel.writeList(this.mReferralHistoryItems);
        parcel.writeString(this.mShareCouponSubject);
        parcel.writeString(this.mShareCouponMessage);
        parcel.writeString(this.mWishCashInfoTitle);
        parcel.writeString(this.mWishCashInfoBody);
        parcel.writeList(this.mInfoBodyTextSpecs);
    }

    public String getFormattedEarnableAmount() {
        return this.mFormattedEarnableAmount;
    }

    public List<WishTextViewSpec> getInfoBodyTextSpecs() {
        return this.mInfoBodyTextSpecs == null ? Collections.emptyList() : this.mInfoBodyTextSpecs;
    }

    public String getCouponCode() {
        return this.mCouponCode;
    }

    public String getWishCashEarnedTextSpec() {
        return this.mWishCashEarnedTextSpec;
    }

    public List<ReferralProgramHistoryItem> getReferralHistoryItems() {
        return this.mReferralHistoryItems;
    }

    public String getShareCouponSubject() {
        return this.mShareCouponSubject;
    }

    public String getShareCouponMessage() {
        return this.mShareCouponMessage;
    }

    public String getWishCashInfoTitle() {
        return this.mWishCashInfoTitle;
    }

    public String getWishCashInfoBody() {
        return this.mWishCashInfoBody;
    }

    public List<WishTextViewSpec> getWarningTextSpecs() {
        return this.mWarningTextSpecs == null ? Collections.emptyList() : this.mWarningTextSpecs;
    }
}
