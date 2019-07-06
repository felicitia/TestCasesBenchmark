package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.threatmetrix.TrustDefender.StrongAuth;
import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class WishPaymentStructureSelectionSpec extends BaseModel implements Parcelable {
    public static final Creator<WishPaymentStructureSelectionSpec> CREATOR = new Creator<WishPaymentStructureSelectionSpec>() {
        public WishPaymentStructureSelectionSpec createFromParcel(Parcel parcel) {
            return new WishPaymentStructureSelectionSpec(parcel);
        }

        public WishPaymentStructureSelectionSpec[] newArray(int i) {
            return new WishPaymentStructureSelectionSpec[i];
        }
    };
    private boolean mCanPayLater;
    private String mFirstAmount;
    private String mFullAmountText;
    private String mInstallmentDueDateText;
    private String mLearnMoreText;
    private int mMaxDaysForPayment;
    private String mPayHalfNowSubText;
    private String mPayHalfNowText;
    private String mSecondAmount;
    private String mTitle;
    private String mWillChargeCardText;

    public int describeContents() {
        return 0;
    }

    public WishPaymentStructureSelectionSpec(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    private WishPaymentStructureSelectionSpec(Parcel parcel) {
        this.mTitle = parcel.readString();
        this.mLearnMoreText = parcel.readString();
        this.mFullAmountText = parcel.readString();
        this.mPayHalfNowText = parcel.readString();
        this.mPayHalfNowSubText = parcel.readString();
        this.mInstallmentDueDateText = parcel.readString();
        this.mWillChargeCardText = parcel.readString();
        this.mCanPayLater = parcel.readByte() != 0;
        this.mMaxDaysForPayment = parcel.readInt();
        this.mFirstAmount = parcel.readString();
        this.mSecondAmount = parcel.readString();
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mTitle = jSONObject.getString(StrongAuth.AUTH_TITLE);
        this.mLearnMoreText = jSONObject.getString("learn_more_text");
        this.mFullAmountText = jSONObject.getString("full_amount_text");
        this.mPayHalfNowText = jSONObject.getString("pay_half_now_text");
        this.mPayHalfNowSubText = jSONObject.getString("pay_half_now_subtext");
        this.mInstallmentDueDateText = jSONObject.getString("payment_due");
        this.mWillChargeCardText = jSONObject.getString("charge_card");
        this.mCanPayLater = jSONObject.getBoolean("can_pay_later");
        this.mMaxDaysForPayment = jSONObject.getInt("max_days_for_payment");
        this.mFirstAmount = jSONObject.getString("first_amount");
        this.mSecondAmount = jSONObject.getString("second_amount");
    }

    public String getTitle() {
        return this.mTitle;
    }

    public String getLearnMoreText() {
        return this.mLearnMoreText;
    }

    public String getFullAmountText() {
        return this.mFullAmountText;
    }

    public String getPayHalfNowText() {
        return this.mPayHalfNowText;
    }

    public String getPayHalfNowSubText() {
        return this.mPayHalfNowSubText;
    }

    public String getInstallmentDueDateText() {
        return this.mInstallmentDueDateText;
    }

    public String getWillChargeCardText() {
        return this.mWillChargeCardText;
    }

    public boolean getCanPayLater() {
        return this.mCanPayLater;
    }

    public int getMaxDaysForPayment() {
        return this.mMaxDaysForPayment;
    }

    public String getFirstAmountText() {
        return this.mFirstAmount;
    }

    public String getSecondAmountText() {
        return this.mSecondAmount;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mTitle);
        parcel.writeString(this.mLearnMoreText);
        parcel.writeString(this.mFullAmountText);
        parcel.writeString(this.mPayHalfNowText);
        parcel.writeString(this.mPayHalfNowSubText);
        parcel.writeString(this.mInstallmentDueDateText);
        parcel.writeString(this.mWillChargeCardText);
        parcel.writeByte(this.mCanPayLater ? (byte) 1 : 0);
        parcel.writeInt(this.mMaxDaysForPayment);
        parcel.writeString(this.mFirstAmount);
        parcel.writeString(this.mSecondAmount);
    }
}
