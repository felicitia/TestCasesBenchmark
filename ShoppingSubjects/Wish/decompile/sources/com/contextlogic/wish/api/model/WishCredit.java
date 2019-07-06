package com.contextlogic.wish.api.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.contextlogic.wish.util.DateUtil;
import com.contextlogic.wish.util.JsonUtil;
import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class WishCredit extends BaseModel implements Parcelable {
    public static final Creator<WishCredit> CREATOR = new Creator<WishCredit>() {
        public WishCredit createFromParcel(Parcel parcel) {
            return new WishCredit(parcel);
        }

        public WishCredit[] newArray(int i) {
            return new WishCredit[i];
        }
    };
    private String mCreditId;
    private String mCustomTitle;
    private Date mExpiryDate;
    private WishLocalizedCurrencyValue mMinimumPurchase;
    private WishLocalizedCurrencyValue mValue;

    public int describeContents() {
        return 0;
    }

    public WishCredit(JSONObject jSONObject) throws JSONException, ParseException {
        super(jSONObject);
    }

    /* access modifiers changed from: protected */
    public void parseJson(JSONObject jSONObject) throws JSONException, ParseException {
        this.mCreditId = jSONObject.getString("id");
        this.mValue = new WishLocalizedCurrencyValue(jSONObject.getDouble("remaining_value"), jSONObject.optJSONObject("localized_remaining_value"));
        if (JsonUtil.hasValue(jSONObject, "expiry_iso")) {
            this.mExpiryDate = DateUtil.parseIsoDate(jSONObject.getString("expiry_iso"));
        }
        if (JsonUtil.hasValue(jSONObject, "minimum_purchase")) {
            this.mMinimumPurchase = new WishLocalizedCurrencyValue(jSONObject.getDouble("minimum_purchase"), jSONObject.optJSONObject("localized_minimum_purchase"));
        }
        if (JsonUtil.hasValue(jSONObject, "custom_title")) {
            this.mCustomTitle = jSONObject.getString("custom_title");
        }
    }

    protected WishCredit(Parcel parcel) {
        if (parcel.readByte() != 0) {
            this.mExpiryDate = new Date(parcel.readLong());
        }
        this.mCreditId = parcel.readString();
        this.mCustomTitle = parcel.readString();
        this.mValue = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
        this.mMinimumPurchase = (WishLocalizedCurrencyValue) parcel.readParcelable(WishLocalizedCurrencyValue.class.getClassLoader());
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (this.mExpiryDate != null ? 1 : 0));
        if (this.mExpiryDate != null) {
            parcel.writeLong(this.mExpiryDate.getTime());
        }
        parcel.writeString(this.mCreditId);
        parcel.writeString(this.mCustomTitle);
        parcel.writeParcelable(this.mValue, 0);
        parcel.writeParcelable(this.mMinimumPurchase, 0);
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        WishCredit wishCredit = (WishCredit) obj;
        if (this.mExpiryDate == null ? wishCredit.mExpiryDate != null : !this.mExpiryDate.equals(wishCredit.mExpiryDate)) {
            return false;
        }
        if (this.mCreditId == null ? wishCredit.mCreditId != null : !this.mCreditId.equals(wishCredit.mCreditId)) {
            return false;
        }
        if (this.mCustomTitle == null ? wishCredit.mCustomTitle != null : !this.mCustomTitle.equals(wishCredit.mCustomTitle)) {
            return false;
        }
        if (this.mValue == null ? wishCredit.mValue != null : !this.mValue.equals(wishCredit.mValue)) {
            return false;
        }
        if (this.mMinimumPurchase != null) {
            z = this.mMinimumPurchase.equals(wishCredit.mMinimumPurchase);
        } else if (wishCredit.mMinimumPurchase != null) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (((((((this.mExpiryDate != null ? this.mExpiryDate.hashCode() : 0) * 31) + (this.mCreditId != null ? this.mCreditId.hashCode() : 0)) * 31) + (this.mCustomTitle != null ? this.mCustomTitle.hashCode() : 0)) * 31) + (this.mValue != null ? this.mValue.hashCode() : 0)) * 31;
        if (this.mMinimumPurchase != null) {
            i = this.mMinimumPurchase.hashCode();
        }
        return hashCode + i;
    }
}
