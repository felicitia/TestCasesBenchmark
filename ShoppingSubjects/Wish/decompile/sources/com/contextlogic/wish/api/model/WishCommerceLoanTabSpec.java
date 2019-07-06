package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCommerceLoanTabSpec extends BaseModel implements Parcelable {
    public static final Creator<WishCommerceLoanTabSpec> CREATOR = new Creator<WishCommerceLoanTabSpec>() {
        public WishCommerceLoanTabSpec createFromParcel(Parcel parcel) {
            return new WishCommerceLoanTabSpec(parcel);
        }

        public WishCommerceLoanTabSpec[] newArray(int i) {
            return new WishCommerceLoanTabSpec[i];
        }
    };
    private String mDescription;
    private String mInvalidBannerTitle;
    private String mInvalidPopupTitle;
    private String mInvalidReasonText;
    private CommerceLoanInvalidReasonType mInvalidReasonType;
    private boolean mIsNew;
    private int mMaxPaymentDays;
    private WishUserBillingInfo mOutstandingLoanBillingInfo;
    private String mOutstandingLoanText;
    private String mTitle;

    public enum CommerceLoanInvalidReasonType implements Parcelable {
        EXCEED_MAX_AMOUNT(1),
        HAS_ISSUED_LOAN(2),
        HAS_DEFAULT_LOAN(3);
        
        public static final Creator<CommerceLoanInvalidReasonType> CREATOR = null;
        private int mValue;

        public int describeContents() {
            return 0;
        }

        static {
            CREATOR = new Creator<CommerceLoanInvalidReasonType>() {
                public CommerceLoanInvalidReasonType createFromParcel(Parcel parcel) {
                    return CommerceLoanInvalidReasonType.values()[parcel.readInt()];
                }

                public CommerceLoanInvalidReasonType[] newArray(int i) {
                    return new CommerceLoanInvalidReasonType[i];
                }
            };
        }

        private CommerceLoanInvalidReasonType(int i) {
            this.mValue = i;
        }

        public static CommerceLoanInvalidReasonType fromInteger(int i) {
            switch (i) {
                case 1:
                    return EXCEED_MAX_AMOUNT;
                case 2:
                    return HAS_ISSUED_LOAN;
                case 3:
                    return HAS_DEFAULT_LOAN;
                default:
                    return null;
            }
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(ordinal());
        }
    }

    public int describeContents() {
        return 0;
    }

    public WishCommerceLoanTabSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    protected WishCommerceLoanTabSpec(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mDescription = parcel.readString();
        this.mIsNew = parcel.readByte() != 0;
        this.mMaxPaymentDays = parcel.readInt();
        this.mInvalidReasonType = (CommerceLoanInvalidReasonType) parcel.readParcelable(CommerceLoanInvalidReasonType.class.getClassLoader());
        this.mInvalidBannerTitle = parcel.readString();
        this.mInvalidPopupTitle = parcel.readString();
        this.mInvalidReasonText = parcel.readString();
        this.mOutstandingLoanText = parcel.readString();
        this.mOutstandingLoanBillingInfo = (WishUserBillingInfo) parcel.readParcelable(WishUserBillingInfo.class.getClassLoader());
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mDescription = jSONObject.getString("description");
        this.mIsNew = jSONObject.getBoolean("is_new");
        this.mMaxPaymentDays = jSONObject.getInt("max_period");
        this.mInvalidBannerTitle = jSONObject.optString("banner_title", "");
        this.mInvalidPopupTitle = jSONObject.optString("popup_title", "");
        this.mInvalidReasonText = jSONObject.optString("reason_text", "");
        this.mInvalidReasonType = CommerceLoanInvalidReasonType.fromInteger(jSONObject.optInt("reason_type"));
        this.mOutstandingLoanText = jSONObject.optString("payment_due_text", "");
        if (jSONObject.has("billing_details") && jSONObject.getJSONObject("billing_details").has("processor_details")) {
            this.mOutstandingLoanBillingInfo = new WishUserBillingInfo(jSONObject.getJSONObject("billing_details").getJSONObject("processor_details"));
        }
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getDescription() {
        return this.mDescription;
    }

    public boolean isNew() {
        return this.mIsNew;
    }

    public int getMaxPaymentDays() {
        return this.mMaxPaymentDays;
    }

    public String getInvalidBannerTitle() {
        return this.mInvalidBannerTitle;
    }

    public String getInvalidPopupTitle() {
        return this.mInvalidPopupTitle;
    }

    public String getInvalidReason() {
        return this.mInvalidReasonText;
    }

    public String getOutstandingLoanText() {
        return this.mOutstandingLoanText;
    }

    public boolean canPayLater() {
        return this.mInvalidReasonType == null;
    }

    public boolean hasOutstandingLoan() {
        return this.mInvalidReasonType == CommerceLoanInvalidReasonType.HAS_ISSUED_LOAN || this.mInvalidReasonType == CommerceLoanInvalidReasonType.HAS_DEFAULT_LOAN;
    }

    public boolean isAboveMaxAmount() {
        return this.mInvalidReasonType == CommerceLoanInvalidReasonType.EXCEED_MAX_AMOUNT;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mDescription);
        parcel.writeByte(this.mIsNew ? (byte) 1 : 0);
        parcel.writeInt(this.mMaxPaymentDays);
        parcel.writeParcelable(this.mInvalidReasonType, 0);
        parcel.writeString(this.mInvalidBannerTitle);
        parcel.writeString(this.mInvalidPopupTitle);
        parcel.writeString(this.mInvalidReasonText);
        parcel.writeString(this.mOutstandingLoanText);
        parcel.writeParcelable(this.mOutstandingLoanBillingInfo, 0);
    }
}
